package datatypes;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtAlimento extends DtDonacion {
	private String descripcionProductos;
	private Integer cantElementos;
	
	public DtAlimento(Integer id, LocalDateTime fechaIngresada, String descripcionProductos, Integer cantElementos) {
		super(id, fechaIngresada);
		this.descripcionProductos = descripcionProductos;
		this.cantElementos = cantElementos;
	}

	public String getDescripcionProductos() {
		return descripcionProductos;
	}

	public Integer getCantElementos() {
		return cantElementos;
	}

	public void setDescripcionProductos(String descripcionProductos) {
		this.descripcionProductos = descripcionProductos;
	}

	public void setCantElementos(Integer cantElementos) {
		this.cantElementos = cantElementos;
	}
	
}