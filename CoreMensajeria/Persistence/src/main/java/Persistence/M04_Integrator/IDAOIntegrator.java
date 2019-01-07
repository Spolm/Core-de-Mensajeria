package Persistence.M04_Integrator;

import Entities.Entity;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Persistence.IDAO;
import java.util.ArrayList;

public interface IDAOIntegrator extends IDAO {

    ArrayList<Entity> listIntegrator() throws DatabaseConnectionProblemException;

    Entity getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    void disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    void enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

}
