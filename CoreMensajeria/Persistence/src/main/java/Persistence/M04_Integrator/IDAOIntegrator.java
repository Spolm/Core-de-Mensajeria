package Persistence.M04_Integrator;

import Entities.Entity;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Persistence.IDAO;
import java.util.ArrayList;

public interface IDAOIntegrator extends IDAO {

    ArrayList<Entity> listIntegrator() throws DatabaseConnectionProblemException;

    ArrayList<Entity> listIntegratorByChannel(int id)throws DatabaseConnectionProblemException,
            ChannelNotFoundException;

    Entity getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    Entity disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    Entity enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;


}
