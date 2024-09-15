package interfaces;

import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtReporte;

import java.util.ArrayList;
import java.util.Date;

import datatypes.Barrio;

import datatypes.DtBeneficiario;

import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import excepciones.DistribucionNoEncontradaExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;
	
	void modificarDistribucion(DtDistribucion distribucion) throws DistribucionNoEncontradaExc;
	
	public void modificarDonacion(DtDonacion donacion);
	
	public DtDistribucion getDistribucion(int idDist);
	
	public ArrayList<DtBeneficiario> ListaBeneficiarios();
	
	public ArrayList<DtBeneficiario> listarBeneficiariosPorEstadoYBarrio(EstadoBeneficiario estado, Barrio barrio);

	public DtDonacion getDonacion(Integer idDon) throws DonacionNoExisteExc;
	
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona);
	
	public Integer [] listarDonaciones();
	
	public ArrayList<DtRepartidor> ListaRepartidores();

	public DtUsuario getUsuario(String email);

	public void modificarUsuario(DtUsuario dtu, String emailNuevo, String nombreNuevo);
	
	public DtBeneficiario getBeneficiario(String email);
	
	public ArrayList<DtReporte> reporte(Date fechaInicial, Date fechaFinal);
}