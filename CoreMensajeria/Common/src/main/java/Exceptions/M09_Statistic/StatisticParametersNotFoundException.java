package Exceptions.M09_Statistic;

import Exceptions.PersonalizedException;

public class StatisticParametersNotFoundException extends PersonalizedException {
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
