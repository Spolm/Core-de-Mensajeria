package M08_Validation.M08_XMLManagement;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M08_SendMessageManager.NullXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandProcessXMLTest {

    private static File _file;
    private static File _fileNull;
    private static Command<VerifiedParameter> _commandProcessXML;
    private static VerifiedParameter verifiedParameter;
    private static String prueba, prueba2, prueba3;
    private static String _text, _textNull;

    @BeforeAll
    public static void init(){
        ClassLoader classLoader = new CommandProcessXMLTest().getClass().getClassLoader();
        _file = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        _fileNull = new File(classLoader.getResource("xml/prueba3.xml").getFile());
        _text = _file.getPath();
        _text = _text.replaceAll("%20"," ");
        _commandProcessXML = CommandsFactory.createCommandProcessXML(_text);
        _textNull = _fileNull.getPath();
        _textNull = _textNull.replaceAll("%20"," ");
        initPruebas();
    }

    @Test
    public void testCommandProcessXML(){
        try {
            _commandProcessXML.execute();
            verifiedParameter = _commandProcessXML.Return();
            assertNotNull(verifiedParameter);
            assertEquals(prueba ,verifiedParameter.get_verifiedMessages().get(0).toString());
            assertEquals(prueba2 ,verifiedParameter.get_verifiedMessages().get(1).toString());
            assertEquals(prueba3 ,verifiedParameter.get_verifiedMessages().get(2).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionCommandProcessXML(){
        assertThrows(NullXMLException.class, () -> {
            _commandProcessXML = CommandsFactory.createCommandProcessXML(_textNull);
            _commandProcessXML.execute();
        });
    }

    private static void initPruebas(){
        prueba = "Message{_param=[ParameterXML{_name='FECHA Inicio', _value='12-12-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='01-03-2020'}], _correo='Carlos@gmail.com', _telefono=''}";
        prueba2 = "Message{_param=[ParameterXML{_name='Fecha Inicio', _value='05-12-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='02-04-2020'}], _correo='Carlos@gmail.com', _telefono=''}";
        prueba3 = "Message{_param=[ParameterXML{_name='Fecha Inicio', _value='11-14-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='12-28-28'}], _correo='Sabrina@gmail.com', _telefono=''}";
    }
}