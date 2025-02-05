package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import datatypes.Barrio;
import datatypes.DtAlimento;
import datatypes.DtArticulo;
import datatypes.DtBeneficiario;
import datatypes.DtDonacion;
import datatypes.DtRepartidor;
import datatypes.DtReporte;
import datatypes.DtUsrModificar;
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;

import excepciones.DonacionRepetidaExc;
import excepciones.RepartidorNoExisteExc;
import excepciones.UsuarioRepetidoExc;
import excepciones.BeneficiarioNoExisteExc;
import excepciones.DistribucionNoEncontradaExc;
import excepciones.DistribucionRepetidaExc;
import excepciones.DonacionNoExisteExc;
import excepciones.UsuarioNOBeneficiarioExc;

import interfaces.IControlador;
import persistencia.Conexion;

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
			throw new DonacionNoExisteExc("La donación no existe");
		}else if (mU.buscarUsuario(dtdistribucion.getBeneficiario().getEmail()) == null){
			throw new UsuarioNOBeneficiarioExc("El usuario no es beneficiario o no existe");
		}else {
			Beneficiario b = null;
			Donacion d = mDon.buscarDonacion(dtdistribucion.getDonacion().getId());
			Usuario usuarioBeneficiario = mU.buscarUsuario(dtdistribucion.getBeneficiario().getEmail());
			if (usuarioBeneficiario instanceof Beneficiario) {
			    b = (Beneficiario) usuarioBeneficiario;
			}
			Distribucion nuevaDistribucion = new Distribucion(dtdistribucion.getId(), dtdistribucion.getFechaPreparacion(), dtdistribucion.getFechaEntrega(), dtdistribucion.getEstado(), b, d);
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
	
	// para no sobrecargar el if de listarLasDistribucionesFiltradas, retorna true si 
	// corresponde añadir la id de la distrubución a la combobox de distribuciones a listar.
	private boolean distribucionCumpleCondiciones(DtDistribucion dt_dist, EstadoDistribucion estado, Barrio zona) {
		boolean filtroEstado = (estado == null || dt_dist.getEstado() == estado);
		boolean filtroZona = (zona == null || dt_dist.getBeneficiario() != null && zona.equals(dt_dist.getBeneficiario().getBarrio()));
		return (filtroEstado && filtroZona);
	}
	
	@Override
	public Integer[] listarLasDistribucionesFiltradas(EstadoDistribucion estado, Barrio zona) {
	    ManejadorDistribucion mD = ManejadorDistribucion.getInstancia();
	    ArrayList<DtDistribucion> todasLasDistribuciones = mD.obtenerDistribuciones();
	    ArrayList<Integer> id_distribuciones = new ArrayList<>();
	    for (DtDistribucion d : todasLasDistribuciones) {
	        if (distribucionCumpleCondiciones(d, estado, zona)) {
	            id_distribuciones.add(d.getId());
	        }
	    }

	    Integer[] distribuciones_ret = new Integer[id_distribuciones.size()];
	    distribuciones_ret = id_distribuciones.toArray(distribuciones_ret);

	    return distribuciones_ret;
	}


	@Override
	public ArrayList<DtBeneficiario> ListaBeneficiarios() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ArrayList<DtBeneficiario> ListaBeneficiarios = new ArrayList<DtBeneficiario>();
		
		ArrayList<Usuario> usuarios = mU.listaUsuarios();  
				
		for (Usuario u :usuarios) {		// importar Usuarios y cargar la lista a DtUsuarios
			if (u instanceof Beneficiario) {
				Beneficiario ben = (Beneficiario) u;
				DtBeneficiario dtben = new DtBeneficiario(
						ben.getNombre(), ben.getEmail(), ben.getPw(), ben.getDireccion(), ben.getFechaNacimiento(), ben.getEstado(), ben.getBarrio());
			ListaBeneficiarios.add(dtben);
			}
		}
	return ListaBeneficiarios;
	}
	
	@Override
    public ArrayList<DtBeneficiario> listarBeneficiariosPorEstadoYBarrio(EstadoBeneficiario estado, Barrio barrio) {
        ArrayList<DtBeneficiario> beneficiarios = ListaBeneficiarios();
        return (ArrayList<DtBeneficiario>) beneficiarios.stream()
                .filter(beneficiario -> beneficiario.getEstado().equals(estado) && beneficiario.getBarrio().equals(barrio))
                .collect(Collectors.toList());
    }
	
	@Override
	public void modificarDistribucion(DtDistribucion dtdistribucion) throws DistribucionNoEncontradaExc {
	    ManejadorDistribucion mDist = ManejadorDistribucion.getInstancia();
	    ManejadorUsuario mU = ManejadorUsuario.getInstancia();
	    ManejadorDonacion mD = ManejadorDonacion.getInstancia();
	    Distribucion distribucionExistente = mDist.buscarDistribucion(dtdistribucion.getId());

        if (distribucionExistente == null) {
            throw new DistribucionNoEncontradaExc("Distribución no encontrada con el ID: " + dtdistribucion.getId());
        } else {
            // Modificar los campos de la distribución existente
            distribucionExistente.setFechaPreparacion(dtdistribucion.getFechaPreparacion());
            distribucionExistente.setFechaEntrega(dtdistribucion.getFechaEntrega());
            distribucionExistente.setEstado(dtdistribucion.getEstado());

			String email = dtdistribucion.getBeneficiario().getEmail();
			Integer id = dtdistribucion.getDonacion().getId();
			
	        Beneficiario beneficiario = (Beneficiario) mU.buscarUsuario(email); //Falta checkeo x si no es beneficiario
	        Donacion donacion = mD.buscarDonacion(id);
	        
	        distribucionExistente.setBeneficiario(beneficiario);
	        distribucionExistente.setDonacion(donacion);
	        
	        Conexion conexion = Conexion.getInstancia();
	        EntityManager em = conexion.getEntityManager();

	        try {
	        	em.getTransaction().begin();
	        	em.merge(distribucionExistente);
	        	em.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	}
	
	@Override
	public Integer [] listarDonaciones() {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
	    ArrayList<DtDonacion> todasLasDonaciones = mD.obtenerDonaciones();
	    ArrayList <Integer> id_donaciones = new ArrayList<>();
	    
	    for (DtDonacion d : todasLasDonaciones) {
	        if (d.getId() != null) {
	            id_donaciones.add(d.getId());
	        }
	    }
	    
		Integer[] retorno = new Integer[id_donaciones.size()];
	    retorno = id_donaciones.toArray(retorno);

		return retorno;	
	}
	
	@Override
	public DtDonacion getDonacion(Integer idDon) throws DonacionNoExisteExc{
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		ArrayList<DtDonacion> todasLasDonaciones = mD.obtenerDonaciones();
		DtDonacion retorno = null;
		
		for (DtDonacion d : todasLasDonaciones) {
			if (d.getId().equals(idDon)) {
				if (d instanceof DtArticulo) {
					DtArticulo articulo = (DtArticulo) d;
					retorno = new DtArticulo(idDon, articulo.getFechaIngresada(), articulo.getDescripcion(), articulo.getPeso(), articulo.getDimensiones());
				} else if (d instanceof DtAlimento) {
					DtAlimento alimento = (DtAlimento) d;
					retorno = new DtAlimento(idDon, alimento.getFechaIngresada(), alimento.getDescripcionProductos(), alimento.getCantElementos());
				}
			}
	    }
		if (retorno == null) {
			throw new DonacionNoExisteExc("La donación no existe");
		}
		return retorno;
	}
	
	@Override
	public DtAlimento getAlimento(Integer idDon) {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		ArrayList<DtDonacion> todasLasDonaciones = mD.obtenerDonaciones();
		DtAlimento retorno = null;
		
		for (DtDonacion d : todasLasDonaciones) {
			if (d.getId().equals(idDon)) {
				if (d instanceof DtAlimento) {
					DtAlimento alimento = (DtAlimento) d;
					retorno = new DtAlimento(idDon, alimento.getFechaIngresada(), alimento.getDescripcionProductos(), alimento.getCantElementos());
				}
			}
	    }
		return retorno;
	}
	
	@Override
	public DtArticulo getArticulo(Integer idDon) {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		ArrayList<DtDonacion> todasLasDonaciones = mD.obtenerDonaciones();
		DtArticulo retorno = null;
		
		for (DtDonacion d : todasLasDonaciones) {
			if (d.getId().equals(idDon)) {
				if (d instanceof DtArticulo) {
					DtArticulo articulo = (DtArticulo) d;
					retorno = new DtArticulo(idDon, articulo.getFechaIngresada(), articulo.getDescripcion(), articulo.getPeso(), articulo.getDimensiones());				}
			}
	    }
		return retorno;
	}
	
	@Override
	public DtBeneficiario getBeneficiario(String email) throws BeneficiarioNoExisteExc {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(email);
		DtBeneficiario dtBen = null;
		if (usr instanceof Beneficiario) {
			Beneficiario usrb = (Beneficiario) usr;
			dtBen = new DtBeneficiario(usrb.getNombre(), usrb.getEmail(),usrb.getPw(), usrb.getDireccion(), usrb.getFechaNacimiento(), usrb.getEstado(), usrb.getBarrio());
		} else if (dtBen == null){
			throw new BeneficiarioNoExisteExc("El beneficiario no existe");
		}
		return dtBen;  // HAY QUE AGREGAR CONTRASEÑA???
	}
	
	@Override
	public DtRepartidor getRepartidor(String email) throws RepartidorNoExisteExc {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(email);
		DtRepartidor dtRep = null;
		if (usr instanceof Repartidor) {
			Repartidor usrr = (Repartidor) usr;
			dtRep = new DtRepartidor(usrr.getNombre(), usrr.getEmail(), usrr.getPw(), usrr.getNumeroLicencia()); // HAY QUE AGREGAR CONTRASEÑA???
		} else if (dtRep == null){
			throw new RepartidorNoExisteExc("El repartidor no existe");
		}
		return dtRep;
	}
	
	@Override
	public void modificarDonacion(DtDonacion donacion) {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		Donacion donacionAModificar = mD.buscarDonacion(donacion.getId());
		
		if (donacionAModificar != null) {
			if (donacion instanceof DtAlimento) {
				DtAlimento alimento = (DtAlimento) donacion;
				Alimento alimentoAModificar = (Alimento) donacionAModificar;
				alimentoAModificar.setId(alimento.getId());
				alimentoAModificar.setFechaIngresada(alimento.getFechaIngresada());
				alimentoAModificar.setDescripcionProductos(alimento.getDescripcionProductos());
				alimentoAModificar.setCantElementos(alimento.getCantElementos());		
			} else if (donacion instanceof DtArticulo) {
				DtArticulo articulo = (DtArticulo) donacion;
				Articulo articuloAModificar = (Articulo) donacionAModificar;
				articuloAModificar.setId(articulo.getId());
				articuloAModificar.setFechaIngresada(articulo.getFechaIngresada());
				articuloAModificar.setDescripcion(articulo.getDescripcion());
				articuloAModificar.setPeso(articulo.getPeso());
				articuloAModificar.setDimensiones(articulo.getDimensiones());	
			}
			
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
	        em.merge(donacionAModificar);
	        em.getTransaction().commit();
		}
	}
	
	public ArrayList<DtRepartidor> ListaRepartidores(){
		ArrayList<DtRepartidor> repartidores = new ArrayList<DtRepartidor>();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ArrayList<Usuario> usuarios = mU.listaUsuarios(); 
		for (Usuario u :usuarios) {		// importar Usuarios y cargar la lista a DtUsuarios
			if (u instanceof Repartidor) {
				Repartidor rep = (Repartidor) u;	// evaluar si es usuario clase repartidor
				DtRepartidor dtrep = new DtRepartidor(
						rep.getNombre(), rep.getEmail(), rep.getPw(), rep.getNumeroLicencia());
				repartidores.add(dtrep);
			}
		}
		return repartidores;
	}
	
	public DtUsuario getUsuario(String email) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(email);
		DtUsuario dtu = null;
		if (usr != null) {
			dtu = new DtUsuario(usr.getNombre(), usr.getEmail(), usr.getPw());
		}
		return dtu;
	}
	
	public void modificarDistribucionesBeneficiario(Beneficiario viejoBeneficiario, Beneficiario nuevoBeneficiario, EntityManager em) { 
	    ManejadorDistribucion mD = ManejadorDistribucion.getInstancia();

	    // Buscar distribuciones asociadas al beneficiario viejo
	    List<Distribucion> distribucionesViejas = mD.buscarDistribucionesPorBeneficiario(viejoBeneficiario.getEmail());

	    if (!(distribucionesViejas.isEmpty())) {
	    	em.getTransaction().begin();
	    	for (Distribucion distribucion : distribucionesViejas) {
	        	distribucion.setBeneficiario(nuevoBeneficiario);	
	        	em.merge(nuevoBeneficiario);	
	        }
	    	em.getTransaction().commit();
	        // ahora se puede eliminar el usuario de la base de datos
	        if (viejoBeneficiario != null) {
	        	em.getTransaction().begin();
	        	em.remove(viejoBeneficiario);
	            em.getTransaction().commit();
	        }
	    }
	}
	
	@Override
	public void modificarUsuario(DtUsrModificar dtu, String emailNuevo, String nombreNuevo, EstadoBeneficiario estadoNuevo, String direccionNueva, Date fechaNacimientoNueva, Barrio barrioNuevo, String numeroDeLicenciaNuevo, String pwNueva) {
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
		String emailActual = dtu.getEmail();
	    ManejadorUsuario mU = ManejadorUsuario.getInstancia();
	    EstadoBeneficiario estadoActual = dtu.getEstado();
	    
	    
	    Beneficiario beneficiarioAModificar = null;
	    Repartidor repartidorAModificar = null;
	    Usuario usuarioAModificar = null;
	    
	    //agrego un poco de lógica pa ver si el usuario a modificar es beneficiario ya que varía la operación del manejador ahora dependiendo de qué sea
	    if (estadoActual != null) { // si es beneficiario
	    	beneficiarioAModificar = mU.buscarBeneficiario(emailActual);
	    } else { //es repartidor
	    	repartidorAModificar = mU.buscarRepartidor(emailActual);
	    }
	    
	    try {
	        em.getTransaction().begin();     
	        if (emailActual.equals(emailNuevo)) { // si el usuario decide no cambiar el email nos ahorramos todo el quilombo con la bd
	        	if (repartidorAModificar != null) {
	        		repartidorAModificar.setNombre(nombreNuevo);
	        		repartidorAModificar.setNumeroLicencia(numeroDeLicenciaNuevo);
	        		repartidorAModificar.setPw(pwNueva);
	        		em.merge(repartidorAModificar);
	        	} else {
	        		beneficiarioAModificar.setNombre(nombreNuevo);
	        		beneficiarioAModificar.setEstado(estadoNuevo);
	        		beneficiarioAModificar.setBarrio(barrioNuevo);
	        		beneficiarioAModificar.setDireccion(direccionNueva);
	        		beneficiarioAModificar.setFechaNacimiento(fechaNacimientoNueva);
	        		beneficiarioAModificar.setPw(pwNueva);
	        		em.merge(beneficiarioAModificar);
	        	}
	        	
                em.getTransaction().commit();
            } else { // NO SE TOCA, NO SE VA A CAMBIAR EL MAIL
		        // Si es Beneficiario
		        if (usuarioAModificar instanceof Beneficiario) {
		            Beneficiario viejoBeneficiario = (Beneficiario) usuarioAModificar;
	
		            Beneficiario nuevoBeneficiario = new Beneficiario(
		                nombreNuevo, emailNuevo, // nuevos email y nombre
		                viejoBeneficiario.getDireccion(), // mismos datos extras
		                viejoBeneficiario.getFechaNacimiento(), 
		                viejoBeneficiario.getEstado(),
		                viejoBeneficiario.getBarrio()
		            );
		            
		            em.merge(nuevoBeneficiario);
		            em.getTransaction().commit();
		            modificarDistribucionesBeneficiario(viejoBeneficiario, nuevoBeneficiario, em); // Pasamos el EntityManager aquí
		           
		        } else if (usuarioAModificar instanceof Repartidor) {
		        	Repartidor viejoRepartidor = (Repartidor) usuarioAModificar;
		        	
		        	Repartidor nuevoRepartidor = new Repartidor(nombreNuevo, emailNuevo,viejoRepartidor.getNumeroLicencia());
		            em.merge(nuevoRepartidor);
		            em.getTransaction().commit();
		            
		            if (viejoRepartidor != null) {
			        	em.getTransaction().begin();
			        	em.remove(viejoRepartidor);
			            em.getTransaction().commit();
			        }
		        }
            }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } 
	}
	
	@Override
	public ArrayList<DtReporte> reporte(Date fechaInicial, Date fechaFinal) {
		ManejadorDistribucion mDist = ManejadorDistribucion.getInstancia();
	    ArrayList<DtDistribucion> distribucionesEnElIntervalo = mDist.obtenerDistribucionesEnIntervalo(fechaInicial, fechaFinal);
	    
	    ArrayList<DtDistribucion> distCordon = new ArrayList<>();
	    ArrayList<DtDistribucion> distCentro = new ArrayList<>();
	    ArrayList<DtDistribucion> distParqueRodo = new ArrayList<>();
	    ArrayList<DtDistribucion> distPalermo = new ArrayList<>();
	    ArrayList<DtDistribucion> distCiudadVieja = new ArrayList<>();
	    
	    ArrayList<DtReporte> retorno = new ArrayList<>();
	    
	    int cantCordon = 0;
	    int cantCentro = 0;
	    int cantParqueRodo = 0;
	    int cantPalermo = 0;
	    int cantCiudadVieja = 0;
	    
	    //iterar y separar zonas
	    for (DtDistribucion dtDist : distribucionesEnElIntervalo) {
	    	Barrio zona = dtDist.getBeneficiario().getBarrio();
	    	if(zona == Barrio.CORDON) {
	    		distCordon.add(dtDist);
	    		cantCordon = cantCordon + 1;
	    	} else if (zona == Barrio.CENTRO) {
	    		distCentro.add(dtDist);
	    		cantCentro = cantCentro + 1;
	    	} else if (zona == Barrio.PARQUE_RODO) {
	    		distParqueRodo.add(dtDist);
	    		cantParqueRodo = cantParqueRodo + 1;
	    	} else if (zona == Barrio.PALERMO) {
	    		distPalermo.add(dtDist);
	    		cantPalermo = cantPalermo + 1;
	    	} else if (zona == Barrio.CIUDAD_VIEJA) {
	    		distCiudadVieja.add(dtDist);
	    		cantCiudadVieja = cantCiudadVieja + 1;
	    	}
	    }
	    
	    DtReporte repCordon = new DtReporte();
	    DtReporte repCentro = new DtReporte();
	    DtReporte repParqueRodo = new DtReporte();
	    DtReporte repPalermo = new DtReporte();
	    DtReporte repCiudadVieja = new DtReporte();
	    
        ArrayList<Object[]> zonas = new ArrayList<>();
        zonas.add(new Object[] { "Cordón", cantCordon, distCordon, repCordon });
        zonas.add(new Object[] { "Centro", cantCentro, distCentro, repCentro });
        zonas.add(new Object[] { "Parque Rodó", cantParqueRodo, distParqueRodo, repParqueRodo });
        zonas.add(new Object[] { "Palermo", cantPalermo, distPalermo, repPalermo });
        zonas.add(new Object[] { "Ciudad Vieja", cantCiudadVieja, distCiudadVieja, repCiudadVieja });

        //Ordenar la lista de zonas por la cantidad de distribuciones (de mayor a menor)
        Collections.sort(zonas, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] zona1, Object[] zona2) {
                return Integer.compare((int) zona2[1], (int) zona1[1]); // comparar por cantidad de distribuciones
            }
        });

        //Imprimir las zonas ordenadas
        for (Object[] zona : zonas) {
            String nombreZona = (String) zona[0];
            int cantidadDistribuciones = (int) zona[1];
            ArrayList<DtDistribucion> distribuciones = (ArrayList<DtDistribucion>) zona[2];
            DtReporte reporteZona = (DtReporte) zona[3];

            if (cantidadDistribuciones != 0) {
            	reporteZona.setBarrio(nombreZona);
            	reporteZona.setCantDist(String.valueOf(cantidadDistribuciones));
            	
                ArrayList<String> beneficiarios = new ArrayList<>();
                for (DtDistribucion d : distribuciones) {
                    if (!beneficiarios.contains(d.getBeneficiario().getEmail())) { //para que no se repitan los mails
                        beneficiarios.add(d.getBeneficiario().getEmail());
                    }
                }
                String todosBeneficiarios = "";
                for (String b : beneficiarios) {
                	todosBeneficiarios = reporteZona.getBeneficiarios();
                	if (todosBeneficiarios != null) {
	                	reporteZona.setBeneficiarios(todosBeneficiarios + " - " + b); //vamos concatenando los emails restantes
                	} else {
                		reporteZona.setBeneficiarios(b); //seteamos el valor inicial
                	}
                
                }
                retorno.add(reporteZona);
            }
            
        }
        return retorno;
    }

}

