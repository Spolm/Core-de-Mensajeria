package M06_DataOrigin;

import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Entities.M06_DataOrigin.ApplicationDAO;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationDAOTest {
    private Application _application;
    private AddApplicationData _addApplicationData;
    private ApplicationDAO _applicationDAO = new ApplicationDAO();

    private void createAddApplicationData(){
        _addApplicationData = new AddApplicationData(
                "Metro de Caracas",
                "Pagina oficial del metro de caracas",
                1,
                1
        );
    }

  /*  @Test
    private void createApplicationsTest(){
        createAddApplicationData();
        try{
            _application = _applicationDAO.createApplication(_addApplicationData);
            assertNotNull(_application);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void getApplicationsTest(){
        try {
            ArrayList<Application> applications = _applicationDAO.getApplications();
            assertTrue(applications.size() > 0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void getApplicationsByCompanyTest(){
        try {
            ArrayList<Application> applications = _applicationDAO.getApplications(
                    _application.get_companyId()
            );
            assertTrue(applications.size() > 0);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void getApplicationByIdTest(){
        try {
            Application application = _applicationDAO.getApplication(_application.get_idApplication());
            assertNotNull(application);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void getApplicationByIdFaildTest(){
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
    private void getApplicationByToken(){
        try {
            Application application = _applicationDAO.getApplication(_application.get_tokenApplication());
            assertNotNull(application);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void getApplicationByTokenFaildTest(){
        try {
            Application application = _applicationDAO.getApplication("9586465");
            assertThrows(ApplicationNotFoundException.class,()->{});
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void updateApplicationByIdTest() {
        try {
            Application application = _applicationDAO.updateApplication(_application.get_idApplication(),0);
            assertEquals(0, application.get_statusApplication());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }*/

}
