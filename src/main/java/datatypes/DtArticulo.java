package datatypes;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtArticulo extends DtDonacion{
	public DtArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtArticulo(Integer id, Date fechaIngresada) {
		super(id, fechaIngresada);
		// TODO Auto-generated constructor stub
	}

	private String descripcion;
	private float peso;
	private String dimensiones;
	
	public DtArticulo(Integer id, Date fechaIngresada, String descripcion, float peso, String dimensiones) {
		super(id, fechaIngresada);
		this.descripcion = descripcion;
		this.peso = peso;
		this.dimensiones = dimensiones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getPeso() {
		return peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	
}