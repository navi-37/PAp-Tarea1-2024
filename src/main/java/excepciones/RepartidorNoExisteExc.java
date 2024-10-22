package excepciones;

public class RepartidorNoExisteExc extends Exception{
	private static final long serialVersionUID = 1L;

    public RepartidorNoExisteExc(String message) {
        super(message);
    }
}