package publicadores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.Barrio;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
//import configuraciones.WebServiceConfiguracion;
import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtReporte;
import datatypes.DtUsrModificar;
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import datatypes.EstadoDistribucion;
import excepciones.BeneficiarioNoExisteExc;
import excepciones.RepartidorNoExisteExc;
import excepciones.DistribucionNoEncontradaExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioNOBeneficiarioExc;
import excepciones.UsuarioRepetidoExc;
import interfaces.Fabrica;
import interfaces.IControlador;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorPublish {
	private Fabrica fabrica;
	private IControlador icon;
	//private WebServiceConfiguracion configuracion;
	private Endpoint endpoint;

	public ControladorPublish() {
		fabrica = Fabrica.getInstancia();
		icon = fabrica.getIControlador();
		//try {
		//	configuracion = new WebServiceConfiguracion();
		//} catch (Exception ex) {
			
		//}
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://" + "127.0.0.1" + ":" + "8083" + "/controlador", this);
		System.out.println("http://" + "127.0.0.1" + ":" + "8083" + "/controlador");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	
	//Nuestros métodos:
	
	@WebMethod
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc {
		icon.altaDonacion(donacion);
	}
	
	@WebMethod
	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc{
		icon.altaUsuario(usuario);
	}
	
	@WebMethod
	public void altaDistribucion(DtDistribucion distribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc{
		icon.altaDistribucion(distribucion);
	}

	@WebMethod
	public void modificarDistribucion(DtDistribucion distribucion) throws DistribucionNoEncontradaExc{
		icon.modificarDistribucion(distribucion);
	}
	
	@WebMethod
	public void modificarDonacion(DtDonacion donacion) {
		icon.modificarDonacion(donacion);
	}
	
	@WebMethod
	public DtDistribucion getDistribucion(int idDist) {
		return icon.getDistribucion(idDist);
	}
	
	@WebMethod
	public DtBeneficiario[] ListaBeneficiarios(){
		ArrayList<DtBeneficiario> listaBeneficiarios = icon.ListaBeneficiarios();
		DtBeneficiario[] retorno = new DtBeneficiario [listaBeneficiarios.size()];
		int i = 0;
		for (DtBeneficiario b : listaBeneficiarios) {
			retorno[i] = b;
			i++;
		}
		return retorno;
	}
	
	@WebMethod
	public DtBeneficiario[] listarBeneficiariosPorEstadoYBarrio(EstadoBeneficiario estado, Barrio barrio){
		ArrayList<DtBeneficiario> listaBenFiltrados = icon.listarBeneficiariosPorEstadoYBarrio(estado, barrio);
		DtBeneficiario[] retorno = new DtBeneficiario [listaBenFiltrados.size()];
		int i = 0;
		for (DtBeneficiario b : listaBenFiltrados) {
			retorno[i] = b;
			i++;
		}
		return retorno;
	}
	
	@WebMethod
	public DtDonacion getDonacion(Integer idDon) throws DonacionNoExisteExc{
		return icon.getDonacion(idDon);
	}
	
	@WebMethod
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona) {
		return icon.listarLasDistribucionesFiltradas(estado, zona);
	}
	
	@WebMethod
	public Integer [] listarDonaciones() {
		return icon.listarDonaciones();
	}
	
	@WebMethod
	public DtRepartidor[] ListaRepartidores(){
		ArrayList<DtRepartidor> listaRepartidores = icon.ListaRepartidores();
		DtRepartidor[] retorno = new DtRepartidor [listaRepartidores.size()];
		int i = 0;
		for (DtRepartidor r : listaRepartidores) {
			retorno[i] = r;
			i++;
		}
		return retorno;
	}
	
	@WebMethod
	public DtUsuario getUsuario(String email) {
		return icon.getUsuario(email);
	}
	
	@WebMethod
	public void modificarUsuario(DtUsrModificar dtu, String emailNuevo, String nombreNuevo, EstadoBeneficiario estadoNuevo, String direccionNueva, LocalDateTime fechaNacimientoNueva, Barrio barrioNuevo, String numeroDeLicenciaNuevo, String pwNueva) {
		icon.modificarUsuario(dtu, emailNuevo, nombreNuevo, estadoNuevo, direccionNueva, fechaNacimientoNueva, barrioNuevo, numeroDeLicenciaNuevo, pwNueva);
	}
	
	@WebMethod
	public DtBeneficiario getBeneficiario(String email) throws BeneficiarioNoExisteExc{
		return icon.getBeneficiario(email);
	}
	
	@WebMethod
	public DtRepartidor getRepartidor(String email) throws RepartidorNoExisteExc{
		return icon.getRepartidor(email);
	}
	
	
	@WebMethod
	public DtReporte[] reporte(Date fechaInicial, Date fechaFinal){
		ArrayList<DtReporte> reporte = icon.reporte(fechaInicial, fechaFinal);
		DtReporte[] retorno = new DtReporte [reporte.size()];
		int i = 0;
		for (DtReporte r : reporte) {
			retorno[i] = r;
			i++;
		}
		return retorno;
	}

}

