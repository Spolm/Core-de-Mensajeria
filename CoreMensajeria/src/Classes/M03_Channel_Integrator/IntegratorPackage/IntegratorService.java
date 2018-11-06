package Classes.M03_Channel_Integrator.IntegratorPackage;

import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntegratorService {
    private static IntegratorService _integratorDAO = null;

    public static IntegratorService getInstance() {
        if (_integratorDAO == null)
            _integratorDAO = new IntegratorService();
        return _integratorDAO;

    }

    public List<Integrator> listIntegrator() {

        Sql db = new Sql();

        List<Integrator> integrators = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT int_id, int_name, int_messageCost, int_threadCapacity, int_tokenApi" +
                    " FROM integrator");

            getIntegratorsRs(integrators, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }finally{
            db.bdClose(db.getConn());
        }
        return integrators;
    }

    public Integrator getConcreteIntegrator(int id) {
        Sql db = new Sql();
        try {
            ResultSet rs = db.sqlConn("SELECT int_id, int_name, int_messageCost, int_threadCapacity, int_tokenApi" +
                    " FROM integrator" +
                    " WHERE int_id = " + id);
            while (rs.next()){
                IntegratorFactory factory = new IntegratorFactory();
                String integratorType = rs.getString("int_name");
                Integrator i = factory.getIntegrator(integratorType, rs.getInt("int_id"), rs.getString("int_name"), rs.getFloat("int_messageCost"),
                        rs.getInt("int_threadCapacity"), rs.getString("int_tokenApi"));
                return i;
            }
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
        }finally{
            db.bdClose(db.getConn());
        }
        return null;
    }

    public static void getIntegratorsRs(List<Integrator> integrators, ResultSet rs) throws SQLException {
        while (rs.next()) {
            IntegratorFactory factory = new IntegratorFactory();
            String integratorType = rs.getString("int_name");
            Integrator i = factory.getIntegrator(integratorType, rs.getInt("int_id"), rs.getString("int_name"), rs.getFloat("int_messageCost"),
                    rs.getInt("int_threadCapacity"), rs.getString("int_tokenApi"));
            integrators.add(i);
        }
    }

}
