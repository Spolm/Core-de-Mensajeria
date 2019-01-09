package Persistence.M04_Integrator;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Entities.M05_Channel.Channel;
import Entities.Sql;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Persistence.DAO;
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

    private Connection _conn;
    private Integrator _integrator;
    private ArrayList<Entity> _integratorList;
    private ResultSet _result;

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
     * Retorna una lista de Integradores
     * Este método retorna una lista de integradores, en caso de no existir
     * el archivo se encontrara en blanco.
     *
     * @return Lista de Integradores
     * @see Integrator
     */

    @Override
    public ArrayList<Entity> listIntegrator() throws DatabaseConnectionProblemException {
         _integratorList = new ArrayList<>();
        try {

           // Integrator _integrator = (Integrator) e;
            _integratorList = new ArrayList<>();
            PreparedStatement st = _conn.prepareCall(SELECT_ALL_INTEGRATORS);
            _result = st.executeQuery();

            while (_result.next()) {
                _integratorList.add(extractIntegrator(_result));
            }
            return _integratorList;
        } catch (SQLException exc) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", exc);
        } finally {
            Sql.bdClose(_conn);
        }
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
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_CONCRETE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                return extractIntegrator(result);
            }
            else {
                throw new IntegratorNotFoundException("El integrador no existe.");
            }
        }
        catch (SQLException exc) {
            throw new DatabaseConnectionProblemException("Error al obtener integrador.", exc);
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
        try {

            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall(UPDATE_DISABLE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return getConcreteIntegrator(id);
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", exc);
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
        try {

            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall(UPDATE_ENABLE_INTEGRATOR);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return getConcreteIntegrator(id);
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al habilitar integrator. ", exc);
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
        _integratorList = new ArrayList<>();
        try {
           // Channel channel = (Channel) e;
            Sql.bdClose(_conn);
            _conn = Sql.getConInstance();
           // ArrayList<Entity> integratorList = new ArrayList<>();
            PreparedStatement preparedStatement = _conn.prepareCall(SELECT_ALL_INTEGRATORS_BY_CHANNEL);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next())
                _integratorList.add(extractIntegrator(result));
            if (_integratorList.size() == 0)
                throw new ChannelNotFoundException("El canal no existe");

            return _integratorList;
        }
        catch (SQLException ex) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", ex);
        }
        finally {
            Sql.bdClose(_conn);
        }
    }

    /**
     *
     * @param rs
     * @return El integrador solicitado
     * @throws SQLException
     */
    private static Integrator extractIntegrator(ResultSet rs) throws SQLException {
         return EntityFactory.CreateIntegrator(rs.getString("int_name"),
                rs.getInt("int_id"), rs.getString("int_name"),
                rs.getFloat("int_messageCost"), rs.getInt("int_threadCapacity"),
                rs.getString("int_tokenApi"), rs.getBoolean("int_enabled"));
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
