package logica;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


//@Entity
@MappedSuperclass
public class Donacion {
	@Id
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