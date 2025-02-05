package datatypes;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtBeneficiario extends DtUsuario {
	private String direccion;
	private Date fechaNacimiento;
	private EstadoBeneficiario estado;
	private Barrio barrio;
		
	public DtBeneficiario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtBeneficiario(String nombre, String email, String pw) {
		super(nombre, email, pw);
		// TODO Auto-generated constructor stub
	}

	public DtBeneficiario(String nombre, String email, String pw, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
		super(nombre, email, pw);
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.barrio = barrio;
	}

	public String getDireccion() {
		return direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public EstadoBeneficiario getEstado() {
		return estado;
	}

	public Barrio getBarrio() {
		return barrio;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEstado(EstadoBeneficiario estado) {
		this.estado = estado;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	@Override
	public String toString() {
		return super.toString() +
		"\n Direccion: " + direccion + "\n  " +
		"\nEstado: " + estado + "\n " +
		"\nBarrio: " + barrio;
	}

}
