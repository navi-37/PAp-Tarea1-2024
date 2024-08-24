package datatypes;

import java.time.LocalDateTime;

import logica.Beneficiario;
import logica.Donacion;

public class DtDistribucion {
	private LocalDateTime fechaPreparacion;
	private LocalDateTime fechaEntrega;
	private EstadoDistribucion estado;
	private Beneficiario beneficiario;
	private Donacion donacion;
	
	
	public DtDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EstadoDistribucion estado, Beneficiario beneficiario, Donacion donacion) {
		super();
		this.fechaPreparacion = fechaPreparacion;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.beneficiario = beneficiario;
		this.donacion = donacion;
	}


	public LocalDateTime getFechaPreparacion() {
		return fechaPreparacion;
	}


	public void setFechaPreparacion(LocalDateTime fechaPreparacion) {
		this.fechaPreparacion = fechaPreparacion;
	}


	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(LocalDateTime fechaEntrega) {
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