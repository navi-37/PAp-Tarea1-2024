package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario{
	private String nombre;
	private String email;
	private String pw;
	
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
		return "Nombre: " + nombre + "\n  " + "\nEmail: " + email;
	}
}
