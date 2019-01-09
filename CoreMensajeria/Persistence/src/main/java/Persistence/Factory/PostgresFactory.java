package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.M07_Template.*;
import Persistence.Postgres.M10_Profile.DAOGeographicalRegionPostgres;
import Persistence.Postgres.M10_Profile.DAOProfilePostgres;

public class PostgresFactory extends DAOAbstractFactory {

    public IDAOProfile createDAOProfile() {
        return new DAOProfilePostgres();
    }

    public IDAOGeographicalRegion createDAOGeographicalRegion(){
        return new DAOGeographicalRegionPostgres();
    }

    //region M07

    public DAOMessage createDaoMessage( ){
        return new DAOMessage();
    }

    public DAOTemplate createDaoTemplate( ){
        return new DAOTemplate();
    }

    public DAOPlanning createDaoPlanning( ){
        return new DAOPlanning();
    }

    public DAOParameter createDaoParameter( ){
        return new DAOParameter();
    }

    public DAOStatus createDAOStatus(){return new DAOStatus();}

    // end region
}