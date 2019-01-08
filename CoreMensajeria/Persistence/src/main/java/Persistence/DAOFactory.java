package Persistence;

import Persistence.M01_Login.GetUserDao;
import Persistence.M04_Integrator.DAOIntegrator;
import Persistence.M05_Channel.DAOChannel;
import Persistence.M01_Login.DAOUser;
import Persistence.M07_Template.DAOMessage;
import Persistence.M07_Template.DAOParameter;
import Persistence.M07_Template.DAOPlanning;
import Persistence.M07_Template.DAOTemplate;
import Persistence.M09_Statistics.DAOStatistic;
import Persistence.M09_Statistics.DAOStatisticEstrella;
import Persistence.M02_Company.DAOCompany;
import Persistence.M03_Campaign.DAOCampaign;

public class DAOFactory {

    //region M01

    static public DAOUser instanciateDaoUser () {
        return new DAOUser();
    }

    //endregion

    //region M04
    static public DAOIntegrator instanciateDaoIntegrator(){return new DAOIntegrator();}
    static public DAOChannel instanciateDaoChannel(){ return new DAOChannel(); }
    //endregion

    //region M09

    static public DAOStatisticEstrella instanciateDaoStatisticsEstrella(){ return new DAOStatisticEstrella(); }
    static public DAOStatistic instanciateDAOStatistic(){ return new DAOStatistic(); }

    //endregion

   static public DAOCompany instanciateDaoCompany ( ) { return new DAOCompany(); }

   static public DAOCampaign instanciateDaoCampaign ( ) { return new DAOCampaign(); }

   //region M07

    static public DAOMessage  instaciateDaoMessage( ){
        return new DAOMessage();
    }

    static public DAOTemplate instaciateDaoTemplate( ){
        return new DAOTemplate();
    }

    static public DAOPlanning instaciateDaoPlanning( ){
        return new DAOPlanning();
    }

    static public DAOParameter instaciateDaoParameter( ){
        return new DAOParameter();
    }

    // end region


}
