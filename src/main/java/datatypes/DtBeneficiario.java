package datatypes;

import java.time.LocalDateTime;


public class DtBeneficiario extends DtUsuario {
	private String direccion;
	private LocalDateTime fechaNacimiento;
	private EstadoBeneficiario estado;
	private Barrio barrio;
	
	public DtBeneficiario(String nombre, String email, String direccion, LocalDateTime fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
		super(nombre, email);
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.barrio = barrio;
	}

	public String getDireccion() {
		return direccion;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public EstadoBeneficiario getEstado() {
		return estado;
	}

	public Barrio getBarrio() {
		return barrio;
	}
	
	@Override
	public String toString() {
		return super.toString() +
		"\nDireccion: " + direccion +
		// "\nFecha de nacimiento: " + fechaNacimiento +  --> dato no usado por ninguna función de listar
		"\nEstado: " + estado +
		"\nBarrio: " + barrio;
	}
}
