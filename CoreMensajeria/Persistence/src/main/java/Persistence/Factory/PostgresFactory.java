package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.IDAO_Statistic;
import Persistence.IDAO_StatisticEstrella;
import Persistence.Postgres.M09_Statistics.*;
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

    public IDAO_StatisticEstrella instanciateDaoStatisticsEstrella() { return new DAOSPostgrestatisticEstrella(); }

    public IDAO_Statistic instanciateDAOStatistic() { return new DAOSPostgrestatistic();  }
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