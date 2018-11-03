package Modulo_3.IntegratorPackage;

import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntegratorService {
    private static IntegratorService integratorDAO = null;

    public static IntegratorService getInstance() {
        if (integratorDAO == null)
            integratorDAO = new IntegratorService();
        return integratorDAO;

    }

    public List<Integrator> listIntegrator() {

        Sql db = new Sql();

        List<Integrator> integrators = new ArrayList<>();

        try {
            ResultSet rs = db.sqlConn("SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi" +
                    " FROM integrator");

            getIntegratorsRs(integrators, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return integrators;
    }

    public Integrator getConcreteIntegrator(int id) {
        Sql db = new Sql();
        try {
            ResultSet rs = db.sqlConn("SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi" +
                    " FROM integrator" +
                    " WHERE in_id = " + id);
            while (rs.next()){
                IntegratorFactory factory = new IntegratorFactory();
                String integratorType = rs.getString("in_name");
                Integrator i = factory.getIntegrator(integratorType, rs.getInt("in_id"), rs.getString("in_name"), rs.getFloat("in_messageCost"),
                        rs.getInt("in_threadCapacity"), rs.getString("in_tokenApi"));
                return i;
            }
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    public static void getIntegratorsRs(List<Integrator> integrators, ResultSet rs) throws SQLException {
        while (rs.next()) {
            IntegratorFactory factory = new IntegratorFactory();
            String integratorType = rs.getString("in_name");
            Integrator i = factory.getIntegrator(integratorType, rs.getInt("in_id"), rs.getString("in_name"), rs.getFloat("in_messageCost"),
                    rs.getInt("in_threadCapacity"), rs.getString("in_tokenApi"));
            integrators.add(i);
        }
    }

}
