package logica;

import java.util.ArrayList;
import java.util.List;

public class ManejadorDonacion {
	private static ManejadorDonacion instancia = null;
	private List<Donacion> donaciones = new ArrayList<>();
	
	private ManejadorDonacion() {} //privado para que no sea instanciable salvo usando getInstancia una unica vez
	
	public static ManejadorDonacion getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorDonacion();
		}
		return instancia;
	}
	
	public void agregarDonacion(Donacion donacion) {
		donaciones.add(donacion);
	}
	
	public Donacion buscarDonacion(Integer id) {
		Donacion retorno = null;
		for (Donacion d : donaciones) {
			if (d.getId().equals(id)) {
				retorno = d;
			}
		}
		return retorno;
	}
}