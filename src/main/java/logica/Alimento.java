package logica;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "alimentos")
public class Alimento extends Donacion {
	private String descripcionProductos;
	private Integer cantElementos;
	
	public Alimento() {}
	
	public Alimento(Integer id, Date fechaIngresada, String descripcionProductos, Integer cantElementos) {
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