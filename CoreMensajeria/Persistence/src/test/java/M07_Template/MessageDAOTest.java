package M07_Template;

import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.Template;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOMessage;
import Persistence.M07_Template.DAOTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageDAOTest {private static String json;
    private static Message _message;
    private static DAOMessage _daoMessage = DAOFactory.instaciateDaoMessage();

    @BeforeAll
    public static void init(){

    }

    @Test
    public void postMessageTest(){
        try{
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
