package Exceptions;

public class IntegratorNotFoundException extends Exception {
    public IntegratorNotFoundException() {
    }

    public IntegratorNotFoundException(Exception e) {
        super(e);
    }

    public IntegratorNotFoundException(String msg) {
        super(msg);
    }

    public IntegratorNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
