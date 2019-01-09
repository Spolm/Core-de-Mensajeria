package Exceptions.M02_Company;

import Exceptions.PersonalizedException;

public class CompanyInvalidDataException extends PersonalizedException {

    public CompanyInvalidDataException(){}

    private String internalMessage = "Internal message no defined";

    public CompanyInvalidDataException(Exception e){
        super(e);
    }

    public CompanyInvalidDataException(String msg){
        super(msg);
    }

    public CompanyInvalidDataException(String msg, Exception e){
        super(msg, e);
    }

    public CompanyInvalidDataException(String msg, Exception e,
                                       String internalMessage){
        super(msg, e);

        this.internalMessage = internalMessage;
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}