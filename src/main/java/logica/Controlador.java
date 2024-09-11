package logica;

import java.util.AbstractMap;
import java.util.Map.Entry;
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
import datatypes.DtUsuario;
import datatypes.EstadoBeneficiario;
import datatypes.DtDistribucion;
import datatypes.EstadoDistribucion;

import excepciones.DonacionRepetidaExc;
import excepciones.UsuarioRepetidoExc;
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
			//DtBeneficiario beneficiario = new DtBeneficiario(b.getNombre(), b.getEmail(), b.getDireccion(), b.getFechaNacimiento(), b.getEstado(), b.getBarrio());
			//DtDonacion donacion = new DtDonacion(d.getId(), d.getFechaIngresada());
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
	
	// hice esto para no sobrecargar el if de listarLasDistribucionesFiltradas, retorna true si 
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
				
		for (Usuario u :usuarios) {					// importar Usuarios y cargar la lista a DtUsuarios
			if (u instanceof Beneficiario) {
				Beneficiario ben = (Beneficiario) u;
				DtBeneficiario dtben = new DtBeneficiario(
						ben.getNombre(), ben.getEmail(), ben.getDireccion(), ben.getFechaNacimiento(), ben.getEstado(), ben.getBarrio());
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
	    Distribucion distribucionExistente = mDist.buscarDistribucion(dtdistribucion.getId());

	    if (distribucionExistente == null) {
	        throw new DistribucionNoEncontradaExc("Distribución no encontrada con el ID: " + dtdistribucion.getId());
	    } else {
	        // Modificar los campos de la distribución existente
	        distribucionExistente.setFechaPreparacion(dtdistribucion.getFechaPreparacion());
	        distribucionExistente.setFechaEntrega(dtdistribucion.getFechaEntrega());
	        distribucionExistente.setEstado(dtdistribucion.getEstado());

	        Beneficiario beneficiario = distribucionExistente.getBeneficiario();
	        Donacion donacion = distribucionExistente.getDonacion();
	        
	        distribucionExistente.setBeneficiario(beneficiario);
	        distribucionExistente.setDonacion(donacion);
	        
	        Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
	        em.merge(distribucionExistente);
	        em.getTransaction().commit();
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
	public DtDonacion getDonacion(Integer idDon) {
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
		return retorno;
	}
	
	@Override
	public DtBeneficiario getBeneficiario(String email) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(email);
		Beneficiario usrb = (Beneficiario) usr;
		DtBeneficiario dtBen = new DtBeneficiario(usrb.getNombre(), usrb.getEmail(), usrb.getDireccion(), usrb.getFechaNacimiento(), usrb.getEstado(), usrb.getBarrio());
		return dtBen;
	}
	
	@Override
	public void modificarDonacion(DtDonacion donacion) {
		ManejadorDonacion mD = ManejadorDonacion.getInstancia();
		Donacion donacionAModificar = mD.buscarDonacion(donacion.getId());
		
		if (donacionAModificar == null) {
			//error
		} else {
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
		for (Usuario u :usuarios) {					// importar Usuarios y cargar la lista a DtUsuarios
			if (u instanceof Repartidor) {
				Repartidor rep = (Repartidor) u;	// evaluar si es usuario clase repartidor
				DtRepartidor dtrep = new DtRepartidor(
						rep.getNombre(), rep.getEmail(), rep.getNumeroLicencia());
				repartidores.add(dtrep);
			}
		}
		return repartidores;
	}
	
	public DtUsuario getUsuario(String email) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		DtUsuario dtu = new DtUsuario(mU.buscarUsuario(email).getNombre(),mU.buscarUsuario(email).getEmail());
		
		return dtu;
	}
	
	public void modificarUsuario(DtUsuario dtu, String email, String nombre) {
		
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
						
		if (!email.equals(dtu.getEmail())) { // si se cambia correo
		    mU.buscarUsuario(dtu.getEmail()).setEmail(email);
		    if (!nombre.equals(dtu.getNombre())) { // y si se cambia el nombre
		    	mU.buscarUsuario(email).setNombre(nombre);
		    }
		} else	// si no se cambia correo pero sí el nombre
			if (!nombre.equals(dtu.getNombre())) { 
			    mU.buscarUsuario(dtu.getEmail()).setNombre(nombre);
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

        // Paso 3: Ordenar la lista de zonas por la cantidad de distribuciones (de mayor a menor)
        Collections.sort(zonas, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] zona1, Object[] zona2) {
                return Integer.compare((int) zona2[1], (int) zona1[1]); // comparar por cantidad de distribuciones
            }
        });

        // Paso 4: Imprimir las zonas ordenadas
        for (Object[] zona : zonas) {
            String nombreZona = (String) zona[0];
            int cantidadDistribuciones = (int) zona[1];
            ArrayList<DtDistribucion> distribuciones = (ArrayList<DtDistribucion>) zona[2];
            DtReporte reporteZona = (DtReporte) zona[3];

            if (cantidadDistribuciones != 0) {
            	reporteZona.setBarrio(nombreZona);
            	reporteZona.setCantDist(String.valueOf(cantidadDistribuciones));
            	
                System.out.println(nombreZona + ":");
                System.out.println("Cantidad de distribuciones: " + cantidadDistribuciones);
                System.out.println("Beneficiarios: ");
                ArrayList<String> beneficiarios = new ArrayList<>();
                for (DtDistribucion d : distribuciones) {
                    if (!beneficiarios.contains(d.getBeneficiario().getEmail())) {
                        beneficiarios.add(d.getBeneficiario().getEmail());
                    }
                }
                String todosBeneficiarios = "";
                for (String b : beneficiarios) {
                	todosBeneficiarios = reporteZona.getBeneficiarios();
                	reporteZona.setBeneficiarios(todosBeneficiarios + b);
                    System.out.println(b);
                }
                retorno.add(reporteZona);
            }
            
        }
        return retorno;
    }
	
	    
	    /*
	    ArrayList<String> beneficiariosCordon = new ArrayList<>();
	    ArrayList<String> beneficiariosCentro = new ArrayList<>();
	    ArrayList<String> beneficiariosParqueRodo = new ArrayList<>();
	    ArrayList<String> beneficiariosPalermo = new ArrayList<>();
	    ArrayList<String> beneficiariosCiudadVieja = new ArrayList<>();
	    
	    if(cantCordon != 0) {
		    System.out.println("Cordón:");
		    for (DtDistribucion d : distCordon) {
		    	if(!beneficiariosCordon.contains(d.getBeneficiario().getEmail())) {
		    		beneficiariosCordon.add(d.getBeneficiario().getEmail());
		    	}
		    }
		    System.out.println("Cantidad de distribuciones: " + cantCordon);
		    System.out.println("Beneficiarios: ");
		    for (String b : beneficiariosCordon) {
	            System.out.println(b);
	        }
	    }
	    
	    if (cantCentro != 0) {
		    System.out.println("Centro:");
		    for (DtDistribucion d : distCentro) {
		    	if(!beneficiariosCentro.contains(d.getBeneficiario().getEmail())) {
		    		beneficiariosCentro.add(d.getBeneficiario().getEmail());
		    	}
		    }
		    System.out.println("Cantidad de distribuciones: " + cantCentro);
		    System.out.println("Beneficiarios: ");
		    for (String b : beneficiariosCentro) {
	            System.out.println(b);
	        }
	    }
	    
	    if (cantParqueRodo != 0) {
		    System.out.println("Parque Rodó:");
		    for (DtDistribucion d : distParqueRodo) {
		    	if(!beneficiariosParqueRodo.contains(d.getBeneficiario().getEmail())) {
		    		beneficiariosParqueRodo.add(d.getBeneficiario().getEmail());
		    	}
		    }
		    System.out.println("Cantidad de distribuciones: " + cantParqueRodo);
		    System.out.println("Beneficiarios: ");
		    for (String b : beneficiariosParqueRodo) {
	            System.out.println(b);
	        }
	    }
	    
	    if (cantPalermo != 0) {
		    System.out.println("Palermo:");
		    for (DtDistribucion d : distPalermo) {
		    	if(!beneficiariosPalermo.contains(d.getBeneficiario().getEmail())) {
		    		beneficiariosPalermo.add(d.getBeneficiario().getEmail());
		    	}
		    }
		    System.out.println("Cantidad de distribuciones: " + cantPalermo);
		    System.out.println("Beneficiarios: ");
		    for (String b : beneficiariosPalermo) {
	            System.out.println(b);
	        }
	    }
	    
	    if (cantCiudadVieja != 0) {
		    System.out.println("Ciudad Vieja:");
		    for (DtDistribucion d : distCiudadVieja) {
		    	if(!beneficiariosCiudadVieja.contains(d.getBeneficiario().getEmail())) {
		    		beneficiariosCiudadVieja.add(d.getBeneficiario().getEmail());
		    	}
		    }
		    System.out.println("Cantidad de distribuciones: " + cantCiudadVieja);
		    System.out.println("Beneficiarios: ");
		    for (String b : beneficiariosCiudadVieja) {
	            System.out.println(b);
	        }
	    }
	    
	    */
}