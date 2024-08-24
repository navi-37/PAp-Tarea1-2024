package logica;

import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtBeneficiario;
import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
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

}