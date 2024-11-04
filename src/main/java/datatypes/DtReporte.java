package datatypes;

public class DtReporte {
	private String barrio;
	private String cantDist;
	private String beneficiarios;
	
	
	public DtReporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtReporte(String barrio, String cantDist, String beneficiarios) {
		super();
		this.barrio = barrio;
		this.cantDist = cantDist;
		this.beneficiarios = beneficiarios;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCantDist() {
		return cantDist;
	}

	public void setCantDist(String cantDist) {
		this.cantDist = cantDist;
	}

	public String getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(String beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	@Override
    public String toString() {
        return "Barrio: " + barrio + "\nDistribuciones: " + cantDist + "\nBeneficiarios: " + beneficiarios + "\n";
    }
	
}
