package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	
	private ManejadorUsuario() {}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	
	public void agregarUsuario(Usuario usuario) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public Usuario buscarUsuario(String email) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Usuario usuario = em.find(Usuario.class, email);
		return usuario;
	}
	
	public Beneficiario buscarBeneficiario(String email) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Beneficiario beneficiario = em.find(Beneficiario.class, email);
		return beneficiario;
	}
	
	public Repartidor buscarRepartidor(String email) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Repartidor repartidor = em.find(Repartidor.class, email);
		return repartidor;
	}
	
	public ArrayList<Usuario> listaUsuarios(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select u from Usuario u");
		
		@SuppressWarnings("unchecked")
		List<Usuario> listUsuario = (List<Usuario>) query.getResultList();
		
		ArrayList<Usuario> aRetornar = new ArrayList<>();
		for(Usuario u: listUsuario) {
			aRetornar.add(u);
		}
		return aRetornar;
	}
}