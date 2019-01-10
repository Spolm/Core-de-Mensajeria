package Exceptions.M04_Integrator;

import Exceptions.PersonalizedException;

public class IntegratorNotFoundException extends PersonalizedException {
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
