package Modulo_3.IntegratorPackage;
import Modulo_3.ConectionDB.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntegratorService {
    private static IntegratorService integratorDAO = null;
    private DbConnection conn;

    IntegratorService() {
        this.conn = conn;
    }

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

            while (rs.next()) {
                IntegratorFactory factory = new IntegratorFactory();
                String integratorType = rs.getString("in_name");
                Integrator i = factory.getIntegrator(integratorType, rs.getInt("in_id"), rs.getString("in_name"), rs.getFloat("in_messageCost"),
                        rs.getInt("in_threadCapacity"), rs.getString("in_tokenApi"));
                integrators.add(i);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return integrators;
    }
}
