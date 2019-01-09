package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

public class CouldNotSendMessageException extends PersonalizedException {
    public CouldNotSendMessageException(){}
    public CouldNotSendMessageException(Exception e){
        super(e);
    }
    public CouldNotSendMessageException(String msg){
        super(msg);
    }
    public CouldNotSendMessageException(String msg, Exception e){
        super(msg, e);
    }
}
