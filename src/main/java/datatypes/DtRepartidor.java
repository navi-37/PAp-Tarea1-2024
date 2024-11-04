package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtRepartidor extends DtUsuario {
    public DtRepartidor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtRepartidor(String nombre, String email, String pw) {
		super(nombre, email, pw);
		// TODO Auto-generated constructor stub
	}

	private String numeroDeLicencia;

    public DtRepartidor(String nombre, String email, String pw, String numeroDeLicencia) {
        super(nombre, email, pw);  
        this.numeroDeLicencia = numeroDeLicencia;
    }

    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
    }

    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }
}
