package Exceptions;

/**
 * Exeption personalizada para indicar que una plantilla no tiene mensaje
 * y cual es el idTemplate que no tiene mensaje.
 */
public class MessageDoesntExistsException extends PersonalizedException{
     private int idTemplate;

    public MessageDoesntExistsException() {
        super();
    }

    public MessageDoesntExistsException(Exception e) {
        super(e);
    }

    public MessageDoesntExistsException(String message, Exception e) {
        super(message, e);
    }

    public MessageDoesntExistsException(String message, Exception e, int idTemplate) {
        super(message, e);
        this.idTemplate = idTemplate;
    }

    public int getIdTemplate() {
        return idTemplate;
    }
}
