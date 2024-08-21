package logica;

import java.time.LocalDateTime;
import datatypes.EstadoBeneficiario;
import datatypes.Barrio;


public class Beneficiario extends Usuario{
	private String direccion;
	private LocalDateTime fechaNacimiento;
	private EstadoBeneficiario estado;
	private Barrio barrio;
	
	public Beneficiario(String nombre, String email, String direccion, LocalDateTime fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
		super(nombre, email);
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.barrio = barrio;
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

	public EstadoBeneficiario getEstado() {
		return estado;
	}

	public void setEstado(EstadoBeneficiario estado) {
		this.estado = estado;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	
}