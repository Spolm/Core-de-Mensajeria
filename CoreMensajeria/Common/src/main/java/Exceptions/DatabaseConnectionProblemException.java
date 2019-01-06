package Exceptions;

public class DatabaseConnectionProblemException extends PersonalizedException{
    public DatabaseConnectionProblemException(){}
    public DatabaseConnectionProblemException(Exception e){
        super(e);
    }
    public DatabaseConnectionProblemException(String msg){
        super(msg);
    }
    public DatabaseConnectionProblemException(String msg, Exception e){
        super(msg, e);
    }
}
