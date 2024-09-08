package logica;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {
	private String nombre;
	@Id
	private String email;
	
	public Usuario() {
		super();
	}

	public Usuario(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}