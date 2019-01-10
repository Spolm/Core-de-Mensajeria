package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.M04_Integrator.DAOIntegrator;
import Persistence.M04_Integrator.IDAOIntegrator;
import Persistence.M05_Channel.DAOChannel;
import Persistence.M05_Channel.IDAOChannel;
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

    @Override
    public IDAOIntegrator createDAOIntegrator() {
        return new DAOIntegrator();
    }

    @Override
    public IDAOChannel createDAOChannel() {
        return new DAOChannel();
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
