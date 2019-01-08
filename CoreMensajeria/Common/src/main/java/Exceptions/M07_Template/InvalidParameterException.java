package Exceptions.M07_Template;

/**
 * Exeption personalizada para indicar que no existe una plantilla
 * y cual es el idTemplate que no existe.
 */
public class InvalidParameterException extends Exception{
    private static final String MESSAGE = "Los parametros ingresados son inválidos";

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(Exception e) {
        super(e);
    }

    public InvalidParameterException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
