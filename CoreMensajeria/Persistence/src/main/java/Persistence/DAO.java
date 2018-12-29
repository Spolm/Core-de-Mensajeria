package Persistence;


import Entities.Registry;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO implements IDAO {

    private static Logger logger = LoggerFactory.getLogger( DAO.class );

    private static Connection conn = null;

    private static Connection conInstance;

    /**
     * Metodo que realiza la conexion con la base de datos
     * @return Conexion hecha a la base de datos
     * @throws ClassNotFoundException Si la clase no es encontrada
     * @throws SQLException Problemas con sql
     * @throws Exception
     * @see Connection
     * @see Statement
     */
    public static Connection getBdConnect()
    {

        try
        {
            Class.forName( Registry.BD_CLASS_FOR_NAME );
            conn = DriverManager.getConnection( Registry.BD_URL, Registry.BD_USER, Registry.BD_PASSWORD );
        }
        catch ( ClassNotFoundException e )
        {
           // logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );

        }
        catch ( SQLException e )
        {
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );

        }
        return conn;
    }

    protected static void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch ( SQLException e )
        {
//            logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }


}
