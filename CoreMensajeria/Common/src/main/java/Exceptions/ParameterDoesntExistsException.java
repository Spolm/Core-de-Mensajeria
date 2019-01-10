package Exceptions;

public class ParameterDoesntExistsException extends PersonalizedException{
    private int id;

    public ParameterDoesntExistsException() {
        super();
    }

    public ParameterDoesntExistsException(Exception e) {
        super(e);
    }

    public ParameterDoesntExistsException(String message, Exception e) {
        super(message, e);
    }

    public ParameterDoesntExistsException(String message, Exception e, int id) {
        super(message, e);
        this.id = id;
    }

    public int getIdTemplate() {
        return id;
    }
}
