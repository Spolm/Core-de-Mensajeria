package Persistence.M04_Integrator;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.Sql;
import Exceptions.M05_Channel.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.M04_Integrator.IntegratorNotFoundException;
import Persistence.DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOIntegrator extends DAO implements IDAOIntegrator {

    private final String SELECT_ALL_INTEGRATORS = "{call m04_getintegrators()}" ;
    private final String SELECT_CONCRETE_INTEGRATOR = "{call m04_getconcreteintegrator(?)}";
    private final String UPDATE_DISABLE_INTEGRATOR = "{call m04_disableintegrator(?)}";
    private final String UPDATE_ENABLE_INTEGRATOR = "{call m04_enableintegrator(?)}";
    private final String SELECT_ALL_INTEGRATORS_BY_CHANNEL = "{call m04_getIntegratorsByChannel(?)}";
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    private Connection _conn;

    /**
    * Constructor que se encarga de realizar la conexión
    * a la base de datos
    *
    * @see Connection
    */
    public DAOIntegrator() {
        this._conn = Sql.getConInstance();
    }

    /**
     * Retorna un solo integrador
     * Este método retorna un integrador en concreto
     * en caso de no existir
     *
     * @param id del integrador a buscar en la base de datos
     * @return Lista de Integradores
     * @see Integrator
     */
    public Entity getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException{
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getConcreteIntegrator ("+id+") DAOIntegrator");
        //endregion
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_CONCRETE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo getConcreteIntegrator("+id+") DAOIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getConcreteIntegrator("+id+") DAOIntegrator");
        //endregion
                return extractIntegrator(result);
            }
            else {
                throw new IntegratorNotFoundException("El integrador no existe.");
            }
        }
        catch (SQLException e) {
        //region Instrumentation Error
        log.error("El metodo getConcreteIntegrator ("+id+") arrojo la excepcion"+ e.getMessage()+"DAOIntegrator");
        //endregion
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        }
    }

    /**
    * Retorna una lista de Integradores
    * Este método retorna una lista de integradores, en caso de no existir
    * el archivo se encontrara en blanco.
    *
    * @return Lista de Integradores
    * @see Integrator
    */
    @Override
    public ArrayList<Entity> listIntegrator() throws DatabaseConnectionProblemException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo listIntegrator DAOIntegrator");
        //endregion
        ArrayList<Entity> integratorList = new ArrayList<>();
        try {
            PreparedStatement st = _conn.prepareCall(SELECT_ALL_INTEGRATORS);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                integratorList.add(extractIntegrator(result));
            }
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo listIntegrator DAOIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo listintegrator  DAOIntegrator");
        //endregion
            return integratorList;
        } catch (SQLException e) {
        //region Instrumentation Error
        log.error("El metodo listintegrator arrojo la excepcion"+ e.getMessage()+"DAOIntegrator");
        //endregion
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        }
    }

    /**
     * Retorna una lista de integradores por canal.
     * Este método retorna una lista de integradores, en caso de no tener
     * el archivo se encontrara en blanco.
     *
     * @return Lista de canales
     * @see Channel
     * @see Integrator
     */
    public ArrayList<Entity> listIntegratorByChannel(int id) throws DatabaseConnectionProblemException,
            ChannelNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo listIntegratorByChannel("+id+") DAOIntegrator");
        //endregion
        ArrayList<Entity> integratorList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_ALL_INTEGRATORS_BY_CHANNEL);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                integratorList.add(extractIntegrator(result));
            }
            if (integratorList.size() == 0) {
                throw new ChannelNotFoundException("No se han encontrado integradores.");
            }
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo listIntegratorByChannel("+id+")DAOIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo listIntegratorByChannel("+id+") DAOIntegrator");
        //endregion
            return integratorList;
        } catch (SQLException e) {
        //region Instrumentation Error
        log.error("El metodo listIntegratorByChannel ("+id+") arrojo la excepcion"+ e.getMessage()+"DAOIntegrator");
        //endregion
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        }
    }

    /**
     * Método que nos permite habilitar integrador
     * permite cambiar el estado del integrador
     *
     * @param id del integrador a buscar en la base de datos
     * @see Integrator
     */
    @Override
    public Entity enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo enableIntegrator("+id+") DAOIntegrator");
        //endregion
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(UPDATE_ENABLE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo enableIntegrator("+id+") DAOIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo enableIntegrator("+id+") DAOIntegrator");
        //endregion
            return getConcreteIntegrator(id);
        } catch (SQLException e) {
        //region Instrumentation Error
        log.error("El metodo enableIntegrator ("+id+") arrojo la excepcion"+ e.getMessage()+"DAOIntegrator");
        //endregion
            throw new DatabaseConnectionProblemException("Error al habilitar integrator. ", e);
        }
    }

    /**
    * Método que nos permite deshabilitar integrador
    * permite cambiar el estado del integrador
    *
    * @param id del integrador a buscar en la base de datos
    * @see Integrator
    */
    @Override
    public Entity disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo disableIntegrator("+id+") DAOIntegrator");
        //endregion
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(UPDATE_DISABLE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo disableIntegrator("+id+") DAOIntegrator");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo del metodo disableIntegrator("+id+") DAOIntegrator");
        //endregion
            return getConcreteIntegrator(id);
        } catch (SQLException e) {
        //region Instrumentation Error
        log.error("El metodo disableIntegrator ("+id+") arrojo la excepcion"+ e.getMessage()+"DAOIntegrator");
        //endregion
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", e);
        }
    }

    /**
    *
    * @param result objeto obtenido de la base de datos.
    * @return El integrador solicitado
    * @throws SQLException en caso de un error en la base de datos
    */
    private static Integrator extractIntegrator(ResultSet result) throws SQLException {
         return EntityFactory.CreateIntegrator(result.getString("int_name"),
                result.getInt("int_id"), result.getString("int_name"),
                result.getFloat("int_messageCost"), result.getInt("int_threadCapacity"),
                result.getString("int_tokenApi"), result.getBoolean("int_enabled"));
    }

    @Override
    public void create(Entity e) {}

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }
}
