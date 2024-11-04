package datatypes;

import java.util.Date;

public class DtDistribucion {
	private int id;
	private Date fechaPreparacion;
	private Date fechaEntrega;
	private EstadoDistribucion estado;
	private DtBeneficiario beneficiario;
	private DtDonacion donacion;
	
	
	public DtDistribucion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtDistribucion(int id, Date fechaPreparacion, Date fechaEntrega, EstadoDistribucion estado, DtBeneficiario beneficiario, DtDonacion donacion) {
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


	public DtBeneficiario getBeneficiario() {
		return beneficiario;
	}


	public void setBeneficiario(DtBeneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}


	public DtDonacion getDonacion() {
		return donacion;
	}


	public void setDonacion(DtDonacion donacion) {
		this.donacion = donacion;
	}

	
}