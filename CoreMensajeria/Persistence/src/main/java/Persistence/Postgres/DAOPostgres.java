package Persistence.Postgres;

import Persistence.IDAO;
import Registry.Registry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOPostgres implements IDAO {
    private static final String PROPERTY_URL = "postgres.url";
    private static final String PROPERTY_DRIVER = "postgres.driver";
    private static final String PROPERTY_USERNAME = "postgres.username";
    private static final String PROPERTY_PASSWORD = "postgres.password";

    protected static Connection getConnection(){
        String url = Registry.getInstance().getProperty(PROPERTY_URL);
        String driver = Registry.getInstance().getProperty(PROPERTY_DRIVER);
        String username = Registry.getInstance().getProperty(PROPERTY_USERNAME);
        String password = Registry.getInstance().getProperty(PROPERTY_PASSWORD);

        Connection connection = null;
        if(driver!=null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}