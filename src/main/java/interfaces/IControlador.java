package interfaces;

import datatypes.DtDonacion;
import datatypes.DtUsuario;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;

public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;
	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
}