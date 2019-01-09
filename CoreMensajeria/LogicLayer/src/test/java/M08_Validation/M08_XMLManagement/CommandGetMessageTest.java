package M08_Validation.M08_XMLManagement;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

class CommandGetMessageTest {

    private static File _file;
    private static Command<VerifiedParameter> _commandProcessXML;
    private static VerifiedParameter _verifiedParameters;
    VerifiedParameter verifiedParameter;
    String prueba;

    @BeforeEach
    public void init(){
        ClassLoader classLoader = new CommandGetMessageTest().getClass().getClassLoader();
        _file = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        _commandProcessXML = CommandsFactory.createCommandProcessXML(_file.getPath());
        initParameters();

    }

    @Test
    public void testCommandProcessXML(){
        try {
            _commandProcessXML.execute();
            verifiedParameter = _commandProcessXML.Return();
            assertNotNull(verifiedParameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initParameters(){
        prueba ="[[Message{_param=[ParameterXML{_name='Fecha Inicio', _value='12-12-2019'}, ParameterXML{_name='Fecha Fin', _value='01-03-2020'}], _correo='Carlos@gmail.com', _telefono=''}]]";
    }

}