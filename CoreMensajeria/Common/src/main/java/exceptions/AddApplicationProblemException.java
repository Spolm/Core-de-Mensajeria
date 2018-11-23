package exceptions;

public class AddApplicationProblemException extends Exception {
    public AddApplicationProblemException() {
    }

    public AddApplicationProblemException(Exception e) {
        super(e);
    }

    public AddApplicationProblemException(String msg) {
        super(msg);
    }

    public AddApplicationProblemException(String msg, Exception e) {
        super(msg, e);
    }
}