package exceptions;

public class NotFoundException extends Exception {
    NotFoundException(){}
    NotFoundException(Exception e){
        super(e);
    }
    NotFoundException(String msg, Exception e){
        super(msg, e);
    }
}
