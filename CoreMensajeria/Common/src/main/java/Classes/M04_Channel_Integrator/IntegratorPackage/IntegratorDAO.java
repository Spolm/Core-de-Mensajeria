package Classes.M04_Channel_Integrator.IntegratorPackage;

import Classes.Sql;
import Exceptions.DatabaseConnectionProblemException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IntegratorDAO {

    final String QUERY_SELECT = "SELECT * FROM integrator";
    final String QUERY_SELECT_BY_ID = "SELECT * FROM iratxr where int_id=?";

    private Connection _conn;
    private Integrator _integrator;
    private ArrayList<Integrator> _integratorList;
    private ResultSet _result;

    public IntegratorDAO(){
        _conn = Sql.getConInstance();
    }

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

    public Integrator getIntegrator(ResultSet rs) throws SQLException {
        Integrator integrator = IntegratorFactory.getIntegrator(rs.getString("int_name"),
                rs.getInt("int_id"), rs.getString("int_name"), rs.getFloat("int_messageCost"),
                rs.getInt("int_threadCapacity"), rs.getString("int_tokenApi"),rs.getBoolean("int_enabled"));
        return integrator;
    }
}