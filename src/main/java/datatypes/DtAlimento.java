package datatypes;

import java.time.LocalDateTime;

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
	
}