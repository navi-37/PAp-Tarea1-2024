package interfaces;

import datatypes.DtDonacion;
import excepciones.DonacionRepetidaExc;

public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;
		
}