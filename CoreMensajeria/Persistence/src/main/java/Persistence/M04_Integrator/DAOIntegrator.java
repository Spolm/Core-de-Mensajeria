package Persistence.M04_Integrator;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.IntegratorDAO;
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
        ArrayList<Entity> _integratorList = new ArrayList<>();
        try {

           // Integrator _integrator = (Integrator) e;
            _integratorList = new ArrayList<>();
            PreparedStatement st = _conn.prepareCall("{call m04_getintegrators()}");
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
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_getConcreteIntegrator(?)}");
            preparedStatement.setInt(1, id);
            _result = preparedStatement.executeQuery();

            _result.next();
            _integrator = extractIntegrator(_result);

            if (_integrator == null) {
                throw new IntegratorNotFoundException("El integrador no existe.");
            }
            return _integrator;

        } catch (SQLException exc) {
            throw new DatabaseConnectionProblemException("Error al obtener integrador.", exc);
        } finally {
            Sql.bdClose(_conn);
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
    public void disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        try {
            //getConcreteIntegrator(id);
            //Integrator _integrator = ( Integrator ) e;
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_disableintegrator(?)}");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", exc);
        } finally {
            Sql.bdClose(_conn);
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
    public void enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        try {
            //getConcreteIntegrator(id);
            //Integrator _integrator = ( Integrator ) e;
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_enableintegrator(?)}");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", exc);
        } finally {
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
        Integrator integrator = EntityFactory.CreateIntegrator(rs.getString("int_name"),
                rs.getInt("int_id"), rs.getString("int_name"),
                rs.getFloat("int_messageCost"), rs.getInt("int_threadCapacity"),
                rs.getString("int_tokenApi"), rs.getBoolean("int_enabled"));
        return integrator;
    }

    @Override
    public void create(Entity e) {

    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }
}
