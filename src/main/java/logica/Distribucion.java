package logica;

import java.util.Date;
import datatypes.EstadoDistribucion;

public class Distribucion {
	private int id;
	private Date fechaPreparacion;
	private Date fechaEntrega;
	private EstadoDistribucion estado;
	private Beneficiario beneficiario;
	private Donacion donacion;
	
	public Distribucion(int id, Date fechaPreparacion, Date fechaEntrega, EstadoDistribucion estado, Beneficiario beneficiario, Donacion donacion) {
		super();
		this.id = id;
		this.fechaPreparacion = fechaPreparacion;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.beneficiario = beneficiario;
		this.donacion = donacion;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaPreparacion() {
		return fechaPreparacion;
	}

	public void setFechaPreparacion(Date fechaPreparacion) {
		this.fechaPreparacion = fechaPreparacion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public EstadoDistribucion getEstado() {
		return estado;
	}

	public void setEstado(EstadoDistribucion estado) {
		this.estado = estado;
	}
	 
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Donacion getDonacion() {
		return donacion;
	}

	public void setDonacion(Donacion donacion) {
		this.donacion = donacion;
	}
	
}