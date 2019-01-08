package Persistence.Factory;

import Persistence.IDAOProfile;
import Persistence.Postgres.M10_Profile.DAOProfilePostgres;

public class PostgresFactory extends DAOAbstractFactory {

    public IDAOProfile createDAOProfile() {
        return new DAOProfilePostgres();
    }
}
