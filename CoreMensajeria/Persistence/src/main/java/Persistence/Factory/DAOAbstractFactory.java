package Persistence.Factory;

import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import Persistence.M04_Integrator.IDAOIntegrator;
import Persistence.M05_Channel.IDAOChannel;
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
    public abstract IDAOIntegrator createDAOIntegrator();
    public abstract IDAOChannel createDAOChannel();
}