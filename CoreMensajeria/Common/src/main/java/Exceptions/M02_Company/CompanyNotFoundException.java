package Exceptions.M02_Company;

import Exceptions.PersonalizedException;

public class CompanyNotFoundException extends PersonalizedException {

    public CompanyNotFoundException(){}

    private int idCompany =-100;

    public CompanyNotFoundException(Exception e){
        super(e);
    }

    public CompanyNotFoundException(String msg){
        super(msg);
    }

    public CompanyNotFoundException(String msg, Exception e){
        super(msg, e);
    }

    public CompanyNotFoundException(String msg,Exception e, int id ){
        super(msg, e);
        this.idCompany = id;
    }

    public int getIdTemplate() {
        return idCompany;
    }
}