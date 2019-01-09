package M07_Template;

import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.Template;
import Persistence.DAOFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.DAOMessage;
import Persistence.M07_Template.DAOTemplate;
import Persistence.M07_Template.IDAOMessage;
import Persistence.M07_Template.IDAOTemplate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDAOTest {private static String json;
    private static Message _message;
    private static int _templateId;
    private static IDAOMessage _daoMessage = DAOAbstractFactory.getFactory().createDaoMessage();
    private static IDAOTemplate _daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();

    @BeforeAll
    public static void init(){
        String _messageText = "PRUEBA";
        int companyId = 1;
        String[] _parameters = {"Monto"};
        _templateId = _daoTemplate.postTemplate(1,1,1);

        _message = (Message) _daoMessage.postMessage(_messageText,companyId,_parameters, _templateId);
    }

    @Test
    public void postMessageTest(){
        try{
            String _messageText = "PRUEBA";
            int companyId = 1;
            String[] _parameters = {"Monto"};
            int _templateIdTest = _daoTemplate.postTemplate(1,1,1);
            Message _messageTest = (Message) _daoMessage.postMessage(_messageText,companyId,_parameters, _templateIdTest);
            _daoMessage.deleteMessage(_messageTest.get_id());
            _daoTemplate.deleteTemplate(_templateIdTest);
            assertNotNull(_messageTest);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMessageTest(){
        try{
            Message _messageTest = (Message) _daoMessage.getMessage(_templateId);
            assertNotNull(_messageTest);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    @Test
    public void getParametersOfMessageTest(){
        try{

            String[] _parametersTest = {"Monto"};
            boolean success = _daoMessage.postParametersOfMessage( _message.get_id(),_parametersTest, 1 );
            assertTrue(success);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateMessageTest(){
        try{
            String[] _parametersTest = {"Monto"};
            _daoMessage.updateMessage("Actualizando",_templateId, _parametersTest, 1);
            Message _messageTest =  (Message)_daoMessage.getMessage( _templateId );
            assertEquals("Actualizando", _messageTest.getMessage());
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    /*@Test
    public void updateParametersOfMessageTest(){
        try{
            String[] _parametersTest = { "Parametro" };
            _daoMessage.updateParameterOfMessage( _message.get_id(),_parametersTest,1 );
            Message _messageTest =  (Message)_daoMessage.getMessage( _templateId );
            assertEquals("Parametro", _messageTest.getParameterArrayList().get(0) );
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }*/

    @AfterAll
    public static void destroy(){
        try{
            _daoMessage.deleteMessage(_message.get_id());
            _daoTemplate.deleteTemplate(_templateId);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
