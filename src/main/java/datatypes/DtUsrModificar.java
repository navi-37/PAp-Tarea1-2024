package datatypes;

import java.time.LocalDateTime;

public class DtUsrModificar {
	private String nombre;
	private String email;
	private String direccion;
	private LocalDateTime fechaNacimiento;
	private EstadoBeneficiario estado;
	private Barrio barrio;
	private String numeroDeLicencia;
	private String pw;
	
	public DtUsrModificar() {
		super();
	}

	public DtUsrModificar(String nombre, String email, String direccion, LocalDateTime fechaNacimiento, EstadoBeneficiario estado, Barrio barrio, String numeroDeLicencia, String pw) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.barrio = barrio;
		this.numeroDeLicencia = numeroDeLicencia;
		this.pw = pw;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public String getNumeroDeLicencia() {
		return numeroDeLicencia;
	}

	public void setNumeroDeLicencia(String numeroDeLicencia) {
		this.numeroDeLicencia = numeroDeLicencia;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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
