package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtDistribucion;
import datatypes.DtDonacion;
import persistencia.Conexion;

public class ManejadorDonacion {
	private static ManejadorDonacion instancia = null;
	private List<Donacion> donaciones = new ArrayList<>();
	
	private ManejadorDonacion() {} //privado para que no sea instanciable salvo usando getInstancia una unica vez
	
	public static ManejadorDonacion getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorDonacion();
		}
		return instancia;
	}
	
	public void agregarDonacion(Donacion donacion) {
		donaciones.add(donacion);
	}
	
	public Donacion buscarDonacion(Integer id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Donacion donacion = em.find(Donacion.class, id);
        return donacion;
    }
	
	public ArrayList<DtDonacion> obtenerDonaciones() {
	    ArrayList<DtDonacion> retorno = new ArrayList<>();
	    for (Donacion d : donaciones) {
	        if (d instanceof Alimento) {
	            Alimento donacionAlimento = (Alimento) d;
	            DtAlimento dt_alimento = new DtAlimento(
	                donacionAlimento.getId(),
	                donacionAlimento.getFechaIngresada(),
	                donacionAlimento.getDescripcionProductos(),
	                donacionAlimento.getCantElementos()
	            );
	            retorno.add(dt_alimento);
	        } else if (d instanceof Articulo) {
	            Articulo donacionArticulo = (Articulo) d;
	            DtArticulo dt_articulo = new DtArticulo(
	                donacionArticulo.getId(),
	                donacionArticulo.getFechaIngresada(),
	                donacionArticulo.getDescripcion(),
	                donacionArticulo.getPeso(),
	                donacionArticulo.getDimensiones()
	            );
	            retorno.add(dt_articulo);
	        }
	    }
	    return retorno;
	}
	
}