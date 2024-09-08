package logica;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo extends Donacion {
	private String descripcion;
	private float peso;
	private String dimensiones;
	
	public Articulo() {}
	
	public Articulo(Integer id, LocalDateTime fechaIngresada, String descripcion, float peso, String dimensiones) {
		super(id, fechaIngresada);
		this.descripcion = descripcion;
		this.peso = peso;
		this.dimensiones = dimensiones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
		
}