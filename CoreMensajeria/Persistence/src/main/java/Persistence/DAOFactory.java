package Persistence;

import Persistence.M01_Login.GetUserDao;
import Persistence.M02_Company.DAOCompany;
import Persistence.M03_Campaign.DAOCampaign;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion


   static public DAOCompany instanciateDaoCompany ( ) { return new DAOCompany(); }

   static public DAOCampaign instanciateDaoCampaign ( ) { return new DAOCampaign(); }


}
