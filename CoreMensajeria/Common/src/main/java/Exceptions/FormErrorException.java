package Exceptions;

public class FormErrorException extends Exception {
    public FormErrorException(String errorMessage) {
        super(errorMessage);
    }
}
