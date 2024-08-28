package logica;

import java.util.ArrayList;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtBeneficiario;
import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;

import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;

import interfaces.IControlador;

public class Controlador implements IControlador{

	public Controlador() {
		super();
	}
	
	@Override
	public void altaDonacion(DtDonacion donacion) throws DonacionRepetidaExc {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		if (mD.buscarDonacion(donacion.getId()) != null) {
			throw new DonacionRepetidaExc("Ya existe una donación con ese id");
		}else {
			Donacion nuevaDonacion = null;
			if (donacion instanceof DtAlimento) {
				nuevaDonacion = new Alimento(donacion.getId(), donacion.getFechaIngresada(), ((DtAlimento)donacion).getDescripcionProductos(), ((DtAlimento) donacion).getCantElementos());
			}
			if (donacion instanceof DtArticulo) {
				nuevaDonacion = new Articulo(donacion.getId(), donacion.getFechaIngresada(), ((DtArticulo)donacion).getDescripcion(), ((DtArticulo)donacion).getPeso(), ((DtArticulo)donacion).getDimensiones());
			}
			mD.agregarDonacion(nuevaDonacion);
		}
	}
	
	@Override
	public void altaDistribucion(DtDistribucion dtdistribucion) throws DistribucionRepetidaExc, UsuarioNOBeneficiarioExc, DonacionNoExisteExc{
		ManejadorDistribucion mDist = ManejadorDistribucion.getInstancia();
		ManejadorDonacion mDon = ManejadorDonacion.getInstancia();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		
		if (mDist.buscarDistribucion(dtdistribucion.getId()) != null) {
			throw new DistribucionRepetidaExc("Ya existe una distribución con este ID");
		}else if (mDon.buscarDonacion(dtdistribucion.getDonacion().getId()) == null) {
			//exc error
			throw new DonacionNoExisteExc("La donación no existe");
		}else if (mU.buscarUsuario(dtdistribucion.getBeneficiario().getEmail()) == null){
			//exc error
			throw new UsuarioNOBeneficiarioExc("El usuario no es beneficiario o no existe");
		}else {
			Beneficiario beneficiario = null;
			Donacion donacion = mDon.buscarDonacion(dtdistribucion.getDonacion().getId());
			Usuario usuarioBeneficiario = mU.buscarUsuario(dtdistribucion.getBeneficiario().getEmail());
			if (usuarioBeneficiario instanceof Beneficiario) {
			    beneficiario = (Beneficiario) usuarioBeneficiario;
			}
			
			Distribucion nuevaDistribucion = new Distribucion(dtdistribucion.getId(), dtdistribucion.getFechaPreparacion(), dtdistribucion.getFechaEntrega(), dtdistribucion.getEstado(), beneficiario, donacion);
			mDist.agregarDistribucion(nuevaDistribucion);
		}
	}

	@Override
	public void altaUsuario(DtUsuario usuario) throws UsuarioRepetidoExc {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.buscarUsuario(usuario.getEmail()) != null) {
			throw new UsuarioRepetidoExc("Ya existe usuario con ese email");
		}else {
			Usuario nuevoUsuario = null;
			if (usuario instanceof DtBeneficiario) {
				nuevoUsuario = new Beneficiario(usuario.getNombre(), usuario.getEmail(), ((DtBeneficiario)usuario).getDireccion(), ((DtBeneficiario) usuario).getFechaNacimiento(), ((DtBeneficiario) usuario).getEstado(), ((DtBeneficiario) usuario).getBarrio());
			}
			if (usuario instanceof DtRepartidor) {
				nuevoUsuario = new Repartidor(usuario.getNombre(), usuario.getEmail(), ((DtRepartidor) usuario).getNumeroDeLicencia());
			}
			mU.agregarUsuario(nuevoUsuario);
		}
	}
	
	@Override
	public DtDistribucion getDistribucion(int idDist) {
		ManejadorDistribucion mD = ManejadorDistribucion.getInstancia();
		ArrayList<DtDistribucion> todasLasDistribuciones = mD.obtenerDistribuciones();
		DtDistribucion retorno = new DtDistribucion(idDist, null, null, null, null, null);
		
		for (DtDistribucion d : todasLasDistribuciones) {
			if (d.getId() == idDist) {
				retorno.setFechaPreparacion(d.getFechaPreparacion());
				retorno.setFechaEntrega(d.getFechaEntrega());
				retorno.setEstado(d.getEstado());
				retorno.setBeneficiario(d.getBeneficiario());
				retorno.setDonacion(d.getDonacion());
			}
	    }
		return retorno;
	}
	
	@Override
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado) {
	    ManejadorDistribucion mD = ManejadorDistribucion.getInstancia();
	    ArrayList<DtDistribucion> todasLasDistribuciones = mD.obtenerDistribuciones();
	    ArrayList<Integer> id_distribuciones = new ArrayList<>();

	    for (DtDistribucion d : todasLasDistribuciones) {
	        if (estado == null || d.getEstado() == estado) {
	            id_distribuciones.add(d.getId());
	        }
	    }

	    Integer[] distribuciones_ret = new Integer[id_distribuciones.size()];
	    distribuciones_ret = id_distribuciones.toArray(distribuciones_ret);

	    return distribuciones_ret;
	}
	
}