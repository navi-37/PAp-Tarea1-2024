package datatypes;

public class DtUsrModificar {
	private String nombre;
	private String email;
	private EstadoBeneficiario estado;
	
	public DtUsrModificar() {
		super();
	}

	public DtUsrModificar(String nombre, String email, EstadoBeneficiario estado) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.estado = estado;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public EstadoBeneficiario getEstado() {
		return estado;
	}

	public void setEstado(EstadoBeneficiario estado) {
		this.estado = estado;
	}
	
	
	@Override
	public String toString() {
		return "Nombre: " + nombre + "\n  " + "\nEmail: " + email;
	}
}
