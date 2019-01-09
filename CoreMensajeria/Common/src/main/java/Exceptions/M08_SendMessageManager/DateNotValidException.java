package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

public class DateNotValidException extends PersonalizedException {

    public DateNotValidException(){}
    public DateNotValidException(Exception e){
        super(e);
    }
    public DateNotValidException(String msg){
        super(msg);
    }
    public DateNotValidException(String msg, Exception e){
        super(msg, e);
    }
}
