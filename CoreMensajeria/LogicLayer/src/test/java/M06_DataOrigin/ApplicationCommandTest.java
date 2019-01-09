package M06_DataOrigin;

import Entities.EntityFactory;
import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Entities.Sql;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M06_DataOrigin.GetApplicationByIdCommand;
import Logic.M06_DataOrigin.GetApplicationByIdCompanyCommand;
import Logic.M06_DataOrigin.GetApplicationCommand;
import Logic.M06_DataOrigin.UpdateApplicationCommand;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Exceptions.ApplicationNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationCommandTest {


    private Application _application;
    private static AddApplicationData _addApplicationData;


    @BeforeEach
    public void before(){
        _addApplicationData=EntityFactory.createAplicationData("Metro de Caracas",
                "Pagina oficial del metro de caracas",1,1);
    }

    @Test
    public void createApplicationsTest() throws Exception {
        try{
            Command c=CommandsFactory.CreateApplication(_addApplicationData);
            c.execute();
            assertNotNull(c.Return());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationsTest() throws Exception {
        try{
            Command c=CommandsFactory.GetApplication();
            c.execute();
            assertTrue(((GetApplicationCommand) c).Return().size()>0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateApplicationByIdTest() throws Exception{
        try {
            Command c = CommandsFactory.UpdateApplication(2, 1);
            c.execute();
            assertEquals(1,((UpdateApplicationCommand) c).Return().get_statusApplication());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByToken()throws Exception {
        try {
            Command c=CommandsFactory.GetApplicationToken("5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD");
            c.execute();
            assertNotNull(c.Return());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByTokenFailTest()throws Exception{
        try {
            Command c=CommandsFactory.GetApplicationToken("9586465");
            c.execute();
            assertThrows(ApplicationNotFoundException.class,()->{});
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByIdFailTest()throws Exception {
        try {
            Command c=CommandsFactory.GetApplicationId(9000);
            c.execute();
            assertThrows(ApplicationNotFoundException.class,()->{});
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByIdTest() throws Exception{
        try {
            Command c=CommandsFactory.GetApplicationId(2);
            c.execute();
            assertNotNull(c.Return());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationsByCompanyTest()throws Exception{
        try {
            Command c=CommandsFactory.GetApplicationCompanyId(3);
            c.execute();
            assertTrue(((GetApplicationByIdCompanyCommand) c).Return().size() > 0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void TearDownClass() throws SQLException{

        Connection conn;
        conn = Sql.getConInstance();
        String query = "DELETE FROM public.application WHERE app_name = 'Metro de Caracas';";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }

}
