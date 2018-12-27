package Persistence;

import Entities.M02_Company.CompanyDAO;
import Persistence.M01_Login.GetUserDao;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion


   static public CompanyDAO instanciateDaoCompany ( ) { return new CompanyDAO ( ); }

}
