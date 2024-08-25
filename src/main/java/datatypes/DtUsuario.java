package datatypes;

public class DtUsuario{
	private String nombre;
	private String email;
	
	public DtUsuario() {
		super();
	}

	public DtUsuario(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nEmail: " + email;
	}
}
