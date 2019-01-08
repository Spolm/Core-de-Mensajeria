package M07_Template;


import Entities.M07_Template.Template;
import Entities.Registry;
import Persistence.DAOFactory;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDAOTest {
    private static String json;
    private static Template _template;
    private static Connection _conn;
    private static String DELETE = "{CALL m07_deletetemplate(?)}";

    @BeforeAll
    public static void init(){
        try{
            Class.forName( Registry.BD_CLASS_FOR_NAME );
            _conn = DriverManager.getConnection( Registry.BD_URL, Registry.BD_USER, Registry.BD_PASSWORD );
            json = "{\n" +
                    "    \"campaign\": 1,\n" +
                    "    \"applicationId\": 1,\n" +
                    "    \"userId\": 1,\n" +
                    "    \"newParameters\": [\"New 52\"],\n" +
                    "    \"company\": 1,\n" +
                    "    \"parameters\": [\n" +
                    "        \"Monto\"\n" +
                    "    ],\n" +
                    "    \"message\": \"Probando [.$Monto$.]\",\n" +
                    "    \"channel_integrator\": [{\n" +
                    "        \"channel\": {\n" +
                    "            \"idChannel\": 1\n" +
                    "        },\n" +
                    "        \"integrator\": {\n" +
                    "            \"idIntegrator\": 1\n" +
                    "        }\n" +
                    "    }],\n" +
                    "    \"planning\": [\n" +
                    "        \"2019-01-09\",\n" +
                    "        \"2019-01-21\",\n" +
                    "        \"22:22\",\n" +
                    "        \"23:02\"\n" +
                    "    ]\n" +
                    "}";
        }catch ( ClassNotFoundException e )
        {
            // logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
        catch (SQLException e )
        {
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }

    }

    @Test
    public void a(){
        try{
            _template = (Template) DAOFactory.instaciateDaoTemplate().postTemplateData(json);
            json = "{\n" +
                    "    \"templateId\": "+_template.get_id()+",\n" +
                    "    \"campaign\": 1,\n" +
                    "    \"applicationId\": 1,\n" +
                    "    \"userId\": 1,\n" +
                    "    \"newParameters\": [\"New 52\"],\n" +
                    "    \"company\": 1,\n" +
                    "    \"parameters\": [\n" +
                    "        \"Monto\"\n" +
                    "    ],\n" +
                    "    \"message\": \"Probando Edicion [.$Monto$.]\",\n" +
                    "    \"channel_integrator\": [{\n" +
                    "        \"channel\": {\n" +
                    "            \"idChannel\": 1\n" +
                    "        },\n" +
                    "        \"integrator\": {\n" +
                    "            \"idIntegrator\": 1\n" +
                    "        }\n" +
                    "    }],\n" +
                    "    \"planning\": [\n" +
                    "        \"2019-01-09\",\n" +
                    "        \"2019-01-21\",\n" +
                    "        \"22:22\",\n" +
                    "        \"23:02\"\n" +
                    "    ]\n" +
                    "}";
            assertNotNull(_template,String.valueOf(_template.get_id()));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void b(){
        try{
            boolean success = DAOFactory.instaciateDaoTemplate().updateTemplateData(json);
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void delete(){
        try{
            PreparedStatement _ps = _conn.prepareCall(DELETE);
            _ps.setInt(1, _template.get_id());
            _ps.executeQuery();
            _conn.close();
        }
        catch (SQLException e )
        {
            //logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }

    }

}
