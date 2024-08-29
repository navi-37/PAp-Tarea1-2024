package interfaces;

import datatypes.DtDonacion;

import java.util.ArrayList;

import datatypes.Barrio;

import datatypes.DtBeneficiario;

import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import logica.Distribucion;
import excepciones.DistribucionNoEncontradaExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;

	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona);
	
	public DtDistribucion getDistribucion(int idDist);
	
	public ArrayList<DtBeneficiario> ListaBeneficiarios();

	void modificarDistribucion(DtDistribucion distribucion) throws DistribucionNoEncontradaExc;

}