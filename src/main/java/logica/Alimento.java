package logica;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Alimento extends Donacion {
	private String descripcionProductos;
	private Integer cantElementos;
	
	public Alimento(Integer id, LocalDateTime fechaIngresada, String descripcionProductos, Integer cantElementos) {
		super(id, fechaIngresada);
		this.descripcionProductos = descripcionProductos;
		this.cantElementos = cantElementos;
	}

	public String getDescripcionProductos() {
		return descripcionProductos;
	}

	public void setDescripcionProductos(String descripcionProductos) {
		this.descripcionProductos = descripcionProductos;
	}

	public Integer getCantElementos() {
		return cantElementos;
	}

	public void setCantElementos(Integer cantElementos) {
		this.cantElementos = cantElementos;
	}
	
}