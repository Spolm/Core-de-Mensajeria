package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.IDAO_Statistic;
import Persistence.IDAO_StatisticEstrella;
import Persistence.Postgres.M09_Statistics.*;
import Persistence.M07_Template.*;
import Registry.Registry;

public abstract class DAOAbstractFactory {
    public static DAOAbstractFactory getFactory(){
        String specificKey = Registry.getInstance().getSpecificKey();
        DAOAbstractFactory factory;
        if(specificKey.equals("postgres")){
            factory = new PostgresFactory();

            // else if -> para otra base de datos
        }else{
            // Throw Exeption later
            // ...
            factory = null;
        }

        return factory;
    }

    /*Crear DAOs usando determinada interfaz*/
    public abstract IDAOProfile createDAOProfile();
    public abstract IDAOGeographicalRegion createDAOGeographicalRegion();
    public abstract IDAO_StatisticEstrella instanciateDaoStatisticsEstrella();
    public abstract IDAO_Statistic instanciateDAOStatistic();

    //region M07

    public abstract IDAOMessage createDaoMessage( );
    public abstract IDAOTemplate createDaoTemplate( );
    public abstract IDAOPlanning createDaoPlanning( );
    public abstract IDAOParameter createDaoParameter( );
    public abstract IDAOStatus createDAOStatus();

    // end region
}