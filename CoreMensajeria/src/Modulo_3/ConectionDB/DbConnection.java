package Modulo_3.ConectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection connection;

    public DbConnection(){
        this.connection = connection;
    }

    public void connect(){
        connection = null;
        String urlDatabase =  "jdbc:postgresql://localhost/proyectods";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDatabase,  "postgres", "1234");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void disconnect(){
        if(connection !=null) try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
