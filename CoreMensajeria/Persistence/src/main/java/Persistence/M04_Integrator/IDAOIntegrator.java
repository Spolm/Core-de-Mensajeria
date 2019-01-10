package Persistence.M04_Integrator;

import Entities.Entity;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.M04_Integrator.IntegratorNotFoundException;
import Persistence.IDAO;
import java.util.ArrayList;

public interface IDAOIntegrator extends IDAO {

    Entity getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    ArrayList<Entity> listIntegrator() throws DatabaseConnectionProblemException;

    ArrayList<Entity> listIntegratorByChannel(int id)throws DatabaseConnectionProblemException,
            ChannelNotFoundException;

    Entity disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;

    Entity enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException;
}
