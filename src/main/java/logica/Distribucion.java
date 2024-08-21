package logica;

import java.time.LocalDateTime;
import datatypes.EstadoDistribucion;

public class Distribucion {
	private LocalDateTime fechaPreparacion;
	private LocalDateTime fechaEntrega;
	private EstadoDistribucion estado;
	
	public Distribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EstadoDistribucion estado) {
		super();
		this.fechaPreparacion = fechaPreparacion;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
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
	 
	
}