package interfaces;

import datatypes.DtDonacion;

import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import logica.Distribucion;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;
	
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado);
	
	public DtDistribucion getDistribucion(int idDist);
}