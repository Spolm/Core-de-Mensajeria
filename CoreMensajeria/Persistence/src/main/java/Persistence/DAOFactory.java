package Persistence;

import Persistence.M01_Login.GetUserDao;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion
}
