package logica;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "repartidores")
public class Repartidor extends Usuario {
	private String numeroLicencia;

	public Repartidor(String nombre, String email, String numeroLicencia) {
		super(nombre, email);
		this.numeroLicencia = numeroLicencia;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}
	
}