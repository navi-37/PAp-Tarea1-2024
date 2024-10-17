package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

//import configuraciones.WebServiceConfiguracion;
import datatypes.DtDonacion;
import excepciones.DonacionRepetidaExc;
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
}
