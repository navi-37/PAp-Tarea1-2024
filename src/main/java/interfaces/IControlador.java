package interfaces;

import datatypes.DtDonacion;
import datatypes.Barrio;
import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;
	
	public void modificarDonacion(DtDonacion donacion);
	

	public DtDistribucion getDistribucion(int idDist);
	
	public DtDonacion getDonacion(Integer idDon);
	
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona);
	
	public Integer [] listarDonaciones();
}