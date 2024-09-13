package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtDonacion;
import datatypes.EstadoDistribucion;
import persistencia.Conexion;

public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;

	private ManejadorDistribucion() {} 

	public static ManejadorDistribucion getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorDistribucion();
		}
		return instancia;
	}

	public void agregarDistribucion(Distribucion distribucion) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(distribucion);
		em.getTransaction().commit();
	}

	public Distribucion buscarDistribucion(int idDistribucion) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Distribucion distribucion = em.find(Distribucion.class, idDistribucion);
		return distribucion;
	}
	
	public ArrayList<DtDistribucion> obtenerDistribuciones(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select d from Distribucion d");
		
		@SuppressWarnings("unchecked")
		List<Distribucion> listDistribucion = (List<Distribucion>) query.getResultList();
		
		ArrayList<DtDistribucion> aRetornar = new ArrayList<>();
		for(Distribucion d: listDistribucion) {
			Beneficiario ben = d.getBeneficiario();
			DtBeneficiario beneficiario = new DtBeneficiario(ben.getNombre(), ben.getEmail(), ben.getDireccion(), ben.getFechaNacimiento(), ben.getEstado(), ben.getBarrio());
			Donacion don = d.getDonacion();
			DtDonacion donacion = new DtDonacion(don.getId(), don.getFechaIngresada());
			DtDistribucion dtdist = new DtDistribucion(d.getId(), d.getFechaPreparacion(),d.getFechaEntrega(),d.getEstado(), beneficiario, donacion);
			aRetornar.add(dtdist);
		}
		return aRetornar;
	}
	
	public ArrayList<Integer> obtenerIDDistribuciones(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select d from Distribucion d");
		
		@SuppressWarnings("unchecked")
		List<Distribucion> listDistribucion = (List<Distribucion>) query.getResultList();
		
		ArrayList<Integer> aRetornar = new ArrayList<>();
		for(Distribucion d: listDistribucion) {
			int indice = d.getId();
			aRetornar.add(indice);
		}
		return aRetornar;
	}
	
	public boolean estaEnIntervalo(Date fecha, Date fechaInicio, Date fechaFin) {
        return (fecha.equals(fechaInicio) || fecha.after(fechaInicio)) &&
               (fecha.equals(fechaFin) || fecha.before(fechaFin));
    }
	
	public ArrayList<DtDistribucion> obtenerDistribucionesEnIntervalo(Date fechaInicial, Date fechaFinal){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select d from Distribucion d");
		
		@SuppressWarnings("unchecked")
		List<Distribucion> listDistribucion = (List<Distribucion>) query.getResultList();
		
		ArrayList<DtDistribucion> aRetornar = new ArrayList<>();
		for(Distribucion d: listDistribucion) {
			Date fecha = d.getFechaEntrega();
			if (estaEnIntervalo(fecha, fechaInicial, fechaFinal)) {
				if(d.getEstado() == EstadoDistribucion.ENTREGADO) { //Para el reporte solo tomamos en cuenta las distribuciones entregadas
					Beneficiario ben = d.getBeneficiario();
					DtBeneficiario beneficiario = new DtBeneficiario(ben.getNombre(), ben.getEmail(), ben.getDireccion(), ben.getFechaNacimiento(), ben.getEstado(), ben.getBarrio());
					Donacion don = d.getDonacion();
					DtDonacion donacion = new DtDonacion(don.getId(), don.getFechaIngresada());
					DtDistribucion dtdist = new DtDistribucion(d.getId(), d.getFechaPreparacion(),d.getFechaEntrega(),d.getEstado(),beneficiario, donacion);
					aRetornar.add(dtdist);
				}
			}
		}
		return aRetornar;
	}
}