package Persistence;

import Persistence.M01_Login.GetUserDao;
import Persistence.M02_Company.DAOCompany;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion


   static public DAOCompany instanciateDaoCompany ( ) { return new DAOCompany(); }

}
