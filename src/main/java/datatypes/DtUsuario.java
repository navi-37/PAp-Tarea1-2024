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

	public DtUsuario(String nombre, String email, String pw) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pw = pw;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\n  " + "\nEmail: " + email;
	}
}
