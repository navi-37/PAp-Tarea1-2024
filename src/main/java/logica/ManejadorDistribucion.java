package logica;

import java.util.ArrayList;
import java.util.List;

import datatypes.DtDistribucion;

public class ManejadorDistribucion {
	private static ManejadorDistribucion instancia = null;
	private List<Distribucion> distribuciones = new ArrayList<>();

	private ManejadorDistribucion() {} 

	public static ManejadorDistribucion getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorDistribucion();
		}
		return instancia;
	}

	public void agregarDistribucion(Distribucion distribucion) {
		distribuciones.add(distribucion);
	}

	public Distribucion buscarDistribucion(int idDistribucion) {
		Distribucion retorno = null;
		for (Distribucion d : distribuciones) {
			if (d.getId() == idDistribucion) {
				retorno = d;
			}
		}
		return retorno;
	}
	
	public ArrayList<DtDistribucion> obtenerDistribuciones(){
		ArrayList<DtDistribucion> retorno = new ArrayList<>();
		for(Distribucion d: distribuciones) {
			DtDistribucion dtdist = new DtDistribucion(d.getId(), d.getFechaPreparacion(),d.getFechaEntrega(),d.getEstado(),d.getBeneficiario(), d.getDonacion());
			retorno.add(dtdist);
		}
		return retorno;
	}
	
	public ArrayList<Integer> obtenerIDDistribuciones(){
		ArrayList<Integer> retorno = new ArrayList<>();
		for(Distribucion d: distribuciones) {
			int indice = d.getId();
			retorno.add(indice);
		}
		return retorno;
	}
	
	
}