package Classes.M04_Integrator;

import Classes.Sql;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;

import java.sql.*;
import java.util.ArrayList;

public class IntegratorDAO {

    final String QUERY_SELECT = "SELECT * FROM integrator";
    final String QUERY_SELECT_BY_ID = "SELECT * FROM integrator where int_id=?";
    final String QUERY_UPDATE_INTEGRATOR = "UPDATE integrator SET int_enabled =? WHERE int_id =?";


    private Connection _conn;
    private Integrator _integrator;
    private ArrayList<Integrator> _integratorList;
    private ResultSet _result;

    /**
     * Constructor que se encarga de realizar la conexion
     * a la base de datos
     * @see Connection
     */
    public IntegratorDAO() {
        _conn = Sql.getConInstance();
    }

    /**
     * Retorna una lista de Integradores
     * Este metodo retorna una lista de integradores, en caso de no existir
     * el archivo se encontrara en blanco.
     * @return      Lista de Integradores
     * @see         Integrator
     */

    public ArrayList<Integrator> listIntegrator() throws DatabaseConnectionProblemException {
        try {
            _integratorList = new ArrayList<>();
            Statement st = _conn.createStatement();
            _result = st.executeQuery(QUERY_SELECT);

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

    public Integrator getConcreteIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException{
        try {
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_SELECT_BY_ID);
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
        if(_integrator == null){
            throw new IntegratorNotFoundException("El integrador no existe.");
        }
        return _integrator;
    }

    public void disableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException{
        try {
            getConcreteIntegrator(id);
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_INTEGRATOR);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. " +QUERY_UPDATE_INTEGRATOR , e);
        } finally {
            Sql.bdClose(_conn);
        }
    }

    public void enableIntegrator(int id) throws DatabaseConnectionProblemException, IntegratorNotFoundException{
        try {
            getConcreteIntegrator(id);
            _conn = Sql.getConInstance();
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_UPDATE_INTEGRATOR);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionProblemException("Error al deshabilitar integrator. " +QUERY_UPDATE_INTEGRATOR , e);
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