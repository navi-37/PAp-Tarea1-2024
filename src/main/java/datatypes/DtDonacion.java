package datatypes;

import java.time.LocalDateTime;

public class DtDonacion {
	private Integer id;
	private LocalDateTime fechaIngresada;
	
	public DtDonacion(Integer id, LocalDateTime fechaIngresada) {
		super();
		this.id = id;
		this.fechaIngresada = fechaIngresada;
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getFechaIngresada() {
		return fechaIngresada;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFechaIngresada(LocalDateTime fechaIngresada) {
		this.fechaIngresada = fechaIngresada;
	}

	
}