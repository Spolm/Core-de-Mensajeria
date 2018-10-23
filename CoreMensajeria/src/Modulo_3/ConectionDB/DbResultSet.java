package Modulo_3.ConectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbResultSet {
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
}
