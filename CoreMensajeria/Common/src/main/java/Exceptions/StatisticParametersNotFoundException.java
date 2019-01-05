package Exceptions;

public class StatisticParametersNotFoundException extends Exception {
    public StatisticParametersNotFoundException(){}
    public StatisticParametersNotFoundException(Exception e){
        super(e);
    }
    public StatisticParametersNotFoundException(String msg){
        super(msg);
    }
    public StatisticParametersNotFoundException(String msg, Exception e){
        super(msg, e);
    }
}
