package logica;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import datatypes.EstadoBeneficiario;
import datatypes.Barrio;

@Entity
@Table(name = "beneficiarios")
public class Beneficiario extends Usuario{
	private String direccion;
	private Date fechaNacimiento;
	@Enumerated(EnumType.STRING) //esto lo hago porque por defecto hibernate mapea los enums como enteros, entonces en la bd se ve un numero en lugar del nombre del barrio, de esta forma se soluciona eso.
	private EstadoBeneficiario estado;
	@Enumerated(EnumType.STRING)
	private Barrio barrio;
	
	public Beneficiario() {}
	
	public Beneficiario(String nombre, String email, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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