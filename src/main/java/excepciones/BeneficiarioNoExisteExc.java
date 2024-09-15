package excepciones;

public class BeneficiarioNoExisteExc extends Exception{
	private static final long serialVersionUID = 1L;

    public BeneficiarioNoExisteExc(String message) {
        super(message);
    }
}