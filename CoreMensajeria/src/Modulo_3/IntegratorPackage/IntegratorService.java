package Modulo_3.IntegratorPackage;
import Modulo_3.ConectionDB.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntegratorService {
    private static IntegratorService integratorDAO = null;
    private DbConnection conn;

    public static IntegratorService getInstance(){
        if(integratorDAO == null)
            integratorDAO = new IntegratorService();
        return integratorDAO;
    }

    IntegratorService(){
        this.conn = conn;
    }

    public static ResultSet getResultSet(DbConnection conector, String query) {
        try {
            PreparedStatement pst = conector.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return null;
    }

    public List<Integrator> listIntegrator(){
        conn = new DbConnection();
        conn.connect();
        List<Integrator> integrators = new ArrayList<>();
        try{
            ResultSet rs = getResultSet(conn, "SELECT in_id, in_name, in_messageCost, in_threadCapacity, in_tokenApi" +
                    "FROM integrator");
            while(rs.next()){
                IntegratorFactory factory = new IntegratorFactory();
                String integratorType = rs.getString("in_name");
                Integrator i = factory.getIntegrator(integratorType, rs.getInt("in_id"), rs.getString("in_name"), rs.getFloat("in_messageCost"),
                        rs.getInt("in_threadCapacity"), rs.getString("in_tokenApi"));
                integrators.add(i);
            }
        }
        catch (SQLException ex){

        }
        return integrators;
    }
}
