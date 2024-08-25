package interfaces;

import datatypes.DtDonacion;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioNOBeneficiarioExc;
import datatypes.DtDistribucion;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;
	
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;
		
}