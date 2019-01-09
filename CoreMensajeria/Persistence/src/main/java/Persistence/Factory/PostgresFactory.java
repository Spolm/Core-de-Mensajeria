package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.Postgres.M10_Profile.DAOGeographicalRegionPostgres;
import Persistence.Postgres.M10_Profile.DAOProfilePostgres;

public class PostgresFactory extends DAOAbstractFactory {

    public IDAOProfile createDAOProfile() {
        return new DAOProfilePostgres();
    }

    public IDAOGeographicalRegion createDAOGeographicalRegion() {
        return new DAOGeographicalRegionPostgres();
    }
}
