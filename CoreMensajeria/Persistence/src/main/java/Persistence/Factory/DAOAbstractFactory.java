package Persistence.Factory;

import Persistence.IDAOProfile;
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

}