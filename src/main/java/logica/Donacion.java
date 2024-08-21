package logica;

import java.time.LocalDateTime;

public class Donacion {
	private Integer id;
	private LocalDateTime fechaIngresada;
	
	public Donacion(Integer id, LocalDateTime fechaIngresada) {
		super();
		this.id = id;
		this.fechaIngresada = fechaIngresada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaIngresada() {
		return fechaIngresada;
	}

	public void setFechaIngresada(LocalDateTime fechaIngresada) {
		this.fechaIngresada = fechaIngresada;
	}
	
}