package datatypes;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtDonacion {
	public DtDonacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	private Date fechaIngresada;
	
	public DtDonacion(Integer id, Date fechaIngresada) {
		super();
		this.id = id;
		this.fechaIngresada = fechaIngresada;
	}

	public Integer getId() {
		return id;
	}

	public Date getFechaIngresada() {
		return fechaIngresada;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFechaIngresada(Date fechaIngresada) {
		this.fechaIngresada = fechaIngresada;
	}

	
}