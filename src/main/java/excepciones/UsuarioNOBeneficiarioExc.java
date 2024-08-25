package excepciones;

public class UsuarioNOBeneficiarioExc extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioNOBeneficiarioExc(String string) {
		super(string);
	}
}
