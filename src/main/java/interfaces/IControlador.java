package interfaces;

import datatypes.DtDonacion;

import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import java.util.ArrayList;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;

	public ArrayList<DtDistribucion> listarDistribuciones();

	public ArrayList<DtDistribucion> listarDistribucionesFiltradas(EstadoDistribucion estado);
	
	public Integer[] listarDistribucionesPorID();
}