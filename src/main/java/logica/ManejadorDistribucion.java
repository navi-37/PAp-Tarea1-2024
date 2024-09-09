package logica;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import datatypes.DtDistribucion;
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
			DtDistribucion dtdist = new DtDistribucion(d.getId(), d.getFechaPreparacion(),d.getFechaEntrega(),d.getEstado(),d.getBeneficiario(), d.getDonacion());
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
}