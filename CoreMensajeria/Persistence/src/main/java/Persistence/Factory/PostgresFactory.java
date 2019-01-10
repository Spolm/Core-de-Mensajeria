package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.M04_Integrator.DAOIntegrator;
import Persistence.M04_Integrator.IDAOIntegrator;
import Persistence.M05_Channel.DAOChannel;
import Persistence.M05_Channel.IDAOChannel;
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
}