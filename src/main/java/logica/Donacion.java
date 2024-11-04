package logica;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Donacion {
	@Id
	private Integer id;
	private Date fechaIngresada;
	
	public Donacion() {}
	
	public Donacion(Integer id, Date fechaIngresada) {
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

	public Date getFechaIngresada() {
		return fechaIngresada;
	}

	public void setFechaIngresada(Date fechaIngresada) {
		this.fechaIngresada = fechaIngresada;
	}
	
}