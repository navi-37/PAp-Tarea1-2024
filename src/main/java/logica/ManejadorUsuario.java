package logica;

import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<>();
	
	private ManejadorUsuario() {} //privado para que no sea instanciable salvo usando getInstancia una unica vez
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Usuario buscarUsuario(String email) {
		Usuario retorno = null;
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				retorno = u;
			}
		}
		return retorno;
	}
}