package M06_DataOrigin;

import Entities.EntityFactory;
import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;

public class ApplicationServiceTest {

    private Application _application;
    private Application _applicationEmpty;
    private ArrayList<Application> applications;
    Date date;

    @BeforeEach
    public void Before(){

        _application= EntityFactory.paramApplication(3,"Banesco Online",
                "Aplicacion web de banesco",	"5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD",
                date,1,	1,3);
        _applicationEmpty=EntityFactory.emptyApplication();
    }

    @Test
    public void ApplicationFailTest(){
        assertNotEquals("[]",_applicationEmpty);
    }

    @Test
    public void CompanyFailIdTest()throws Exception{
        Command c= CommandsFactory.GetApplicationCompanyId(100);
        c.execute();
        assertNotEquals("[]",c.Return());
    }

    @Test
    public void ApplicationFailIdTest(){
        assertNotEquals(1,_application.get_id());
    }

    @Test
    public void ApplicationFailTokenTest(){
        assertNotEquals("awdawd",_application.get_tokenApplication());
    }

    @Test
    public  void ApplicationFailActive(){
        assertNotEquals("0",_application.get_statusApplication());
    }

    @Test
    public void ApplicationFailCreate(){
        assertEquals(0,_applicationEmpty.get_statusApplication());
        assertEquals(0,_applicationEmpty.get_companyId());
        assertNotEquals("0",_applicationEmpty.get_descriptionApplication());
        assertEquals(0,_applicationEmpty.get_idApplication());
        assertNotEquals("",_applicationEmpty.get_tokenApplication());
    }
}
