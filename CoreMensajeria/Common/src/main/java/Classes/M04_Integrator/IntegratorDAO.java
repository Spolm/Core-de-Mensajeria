package Classes.M04_Integrator;

import Classes.Sql;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase que nos permite realizar la conexion a la base de datos
 * con los metodos relacionados a la clase integrador
 *
 * @Author Josè Salas
 * @Author Manuel Espinoza
 * @Author Josè Cedeño
 * @see Integrator
 */

public class IntegratorDAO {

    private Connection _conn;
    private Integrator _integrator;
    private ArrayList<Integrator> _integratorList;
    private ResultSet _result;

    /**
     * Constructor que se encarga de realizar la conexion
     * a la base de datos
     *
     * @see Connection
     */

    public IntegratorDAO() {
        _conn = Sql.getConInstance();
    }

    /**
     * Retorna una lista de Integradores
     * Este metodo retorna una lista de integradores, en caso de no existir
     * el archivo se encontrara en blanco.
     *
     * @return Lista de Integradores
     * @see Integrator
     */

    public ArrayList<Integrator> listIntegrator() throws DatabaseConnectionProblemException {
        try {
            _integratorList = new ArrayList<>();
            PreparedStatement st = _conn.prepareCall("{call m04_getintegrators()}");
            _result = st.executeQuery();

            while (_result.next()) {
                _integratorList.add(getIntegrator(_result));
            }
            return _integratorList;
        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error de comunicacion con la base de datos.", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    /**
     * Retorna un solo integrador
     * Este metodo retorna un integrador en concreto
     * en caso de no existir
     *
     * @param id del integrador a buscar en la base de datos
     * @return Lista de Integradores
     * @see Integrator
     */

    public Integrator getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        try {
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_getConcreteIntegrator(?)}");
            preparedStatement.setInt(1, id);
            _result = preparedStatement.executeQuery();
            _integrator = null;

            while (_result.next()) {
                _integrator = getIntegrator(_result);
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionProblemException("Error al obtener integrador.", e);
        } finally {
            Sql.bdClose(_conn);
        }
        if (_integrator == null) {
            throw new IntegratorNotFoundException("El integrador no existe.");
        }
        return _integrator;
    }

    /**
     * Metodo que nos permite deshabilitar integrador
     * permite cambiar el estado del integrador
     *
     * @param id del integrador a buscar en la base de datos
     * @see Integrator
     */

    public void disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        try {
            getConcreteIntegrator(id);
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_disableintegrator(?)}");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    /**
     * Metodo que nos permite habilitar integrador
     * permite cambiar el estado del integrador
     *
     * @param id del integrador a buscar en la base de datos
     * @see Integrator
     */

    public void enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException {
        try {
            getConcreteIntegrator(id);
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareCall("{call m04_enableintegrator(?)}");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. ", e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public static Integrator getIntegrator(ResultSet rs) throws SQLException {
        Integrator integrator = IntegratorFactory.getIntegrator(rs.getString("int_name"),
                rs.getInt("int_id"), rs.getString("int_name"),
                rs.getFloat("int_messageCost"), rs.getInt("int_threadCapacity"),
                rs.getString("int_tokenApi"), rs.getBoolean("int_enabled"));
        return integrator;
    }
}