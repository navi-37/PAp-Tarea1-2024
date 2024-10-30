package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtRepartidor extends DtUsuario {
    private String numeroDeLicencia;

    public DtRepartidor(String nombre, String email, String numeroDeLicencia) {
        super(nombre, email);  
        this.numeroDeLicencia = numeroDeLicencia;
    }

    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
    }

    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }
}
