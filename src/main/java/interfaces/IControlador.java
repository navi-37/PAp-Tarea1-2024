package interfaces;

import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtReporte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import datatypes.Barrio;
import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtBeneficiario;

import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import datatypes.EstadoDistribucion;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import datatypes.DtUsrModificar;
import excepciones.BeneficiarioNoExisteExc;
import excepciones.DistribucionNoEncontradaExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;
import excepciones.RepartidorNoExisteExc;


public interface IControlador {
	
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc;

	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc;
  
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc;
	
	public void modificarDistribucion(DtDistribucion distribucion) throws DistribucionNoEncontradaExc;
	
	public void modificarDonacion(DtDonacion donacion);
	
	public DtDistribucion getDistribucion(int idDist);
	
	public ArrayList<DtBeneficiario> ListaBeneficiarios();
	
	public ArrayList<DtBeneficiario> listarBeneficiariosPorEstadoYBarrio(EstadoBeneficiario estado, Barrio barrio);

	public DtDonacion getDonacion(Integer idDon) throws DonacionNoExisteExc;
	
	public DtAlimento getAlimento(Integer idDon);
	
	public DtArticulo getArticulo(Integer idDon);
	
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona);
	
	public Integer [] listarDonaciones();
	
	public ArrayList<DtRepartidor> ListaRepartidores();

	public DtUsuario getUsuario(String email);

	public void modificarUsuario(DtUsrModificar dtu, String emailNuevo, String nombreNuevo, EstadoBeneficiario estadoNuevo, String direccionNueva, LocalDateTime fechaNacimientoNueva, Barrio barrioNuevo, String numeroDeLicenciaNuevo, String pwNueva);
	
	public DtBeneficiario getBeneficiario(String email) throws BeneficiarioNoExisteExc;
	
	public DtRepartidor getRepartidor(String email) throws RepartidorNoExisteExc;
	
	public ArrayList<DtReporte> reporte(Date fechaInicial, Date fechaFinal);
}