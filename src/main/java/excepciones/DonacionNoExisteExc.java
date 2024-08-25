package excepciones;

public class DonacionNoExisteExc extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DonacionNoExisteExc(String string) {
		super(string);
	}
}