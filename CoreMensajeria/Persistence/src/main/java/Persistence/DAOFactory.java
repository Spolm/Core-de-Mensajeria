package Persistence;

import Persistence.M01_Login.DAOUser;

public class DAOFactory {

    //region M01

    static public DAOUser instanciateDaoUser () {
        return new DAOUser();
    }

    //endregion
}
