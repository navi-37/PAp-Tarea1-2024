package excepciones;

public class DonacionRepetidaExc extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DonacionRepetidaExc(String string) {
		super(string);
	}
}
