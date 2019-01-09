package M06_DataOrigin;

import Entities.EntityFactory;
import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.M06_DataOrigin.DAOApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;

public class DAOApplicationTest {

    private Application _application;
    private AddApplicationData _addApplicationData;
    private DAOApplication _applicationDAO=new DAOApplication();
    private ArrayList<Application> applications = null;

    @BeforeEach
    private void Before(){
        _addApplicationData= EntityFactory.createAplicationData("Metro de Caracas",
                "Pagina oficial del metro de caracas",1,1);

    }

    @Test
    public void getApplicationsTest(){
        try {
            ArrayList<Application> applications = _applicationDAO.getApplication();
            assertTrue(applications.size() > 0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationsByCompanyTest(){
        try {
            applications = _applicationDAO.getApplications(_addApplicationData.get_companyId());
            assertTrue(applications.size() > 0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByIdTest(){
        try {
            _application = _applicationDAO.getApplication(3);
            assertNotNull(_application);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByIdFaildTest(){
        try {
            Application application = _applicationDAO.getApplication(9000);
            assertThrows(ApplicationNotFoundException.class,()->{});
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByToken(){
        try {
            Application application = _applicationDAO.getApplication("5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD");
            assertNotNull(application);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicationByTokenFaildTest(){
        try {
            Application application = _applicationDAO.getApplication("9586465");
            assertThrows(ApplicationNotFoundException.class,()->{});
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

}
