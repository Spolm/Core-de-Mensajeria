package M08_Validation.M08_XMLManagement;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M08_SendMessageManager.NullXMLException;
import Exceptions.M08_SendMessageManager.TemplateNotApprovedException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandProcessXMLTest {

    private static File _file;
    private static File _fileNull;
    private static File _fileDisabled;
    private static Command<VerifiedParameter> _commandProcessXML;
    private static VerifiedParameter verifiedParameter;
    private static String prueba, prueba2;
    private static String _text, _textNull, _textDisabled;

    @BeforeAll
    public static void init(){
        ClassLoader classLoader = new CommandProcessXMLTest().getClass().getClassLoader();
        _file = new File(classLoader.getResource("xml/prueba7.xml").getFile());
        _fileNull = new File(classLoader.getResource("xml/prueba4.xml").getFile());
        _fileDisabled = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        _text = _file.getPath();
        _text = _text.replaceAll("%20"," ");
        _textNull = _fileNull.getPath();
        _textNull = _textNull.replaceAll("%20"," ");
        _textDisabled = _fileDisabled.getPath();
        _textDisabled = _textDisabled.replaceAll("%20"," ");
    }

    @BeforeEach
    void cleanValues(){
        _commandProcessXML = null;
    }

    @Test
    public void testCommandProcessXML(){
        try {
            initPruebas();
            _commandProcessXML = CommandsFactory.createCommandProcessXML(_text);
            _commandProcessXML.execute();
            verifiedParameter = _commandProcessXML.Return();
        } catch (Exception e) {
            Assert.fail( e.getMessage() );
        }
        assertNotNull(verifiedParameter);
        assertEquals(prueba ,verifiedParameter.get_verifiedMessages().get(0).toString());
        assertEquals(prueba2 ,verifiedParameter.get_verifiedMessages().get(1).toString());

    }

    @Test
    public void testExceptionCommandProcessXML(){
        assertThrows(NullXMLException.class, () -> {
            _commandProcessXML = CommandsFactory.createCommandProcessXML(_textNull);
            _commandProcessXML.execute();
        });
    }

    @Test
    public void testDisabledTemplateException(){
        assertThrows( TemplateNotApprovedException.class, () -> {
            _commandProcessXML = CommandsFactory.createCommandProcessXML(_textDisabled);
            _commandProcessXML.execute();
        });
    }

    private static void initPruebas(){
        prueba = "Message{_param=[ParameterXML{_name='Evento', _value='Caracas Vs Magallanes'}," +
                " ParameterXML{_name='Fecha', _value='01-03-2020'}, ParameterXML{_name='Hora', _value='12:27 PM'}," +
                " ParameterXML{_name='Lugar', _value='Valencia'}], _correo='', _telefono='0416-2184569'}";
        prueba2 = "Message{_param=[ParameterXML{_name='Evento', _value='Caracas Vs Magallanes'}," +
                " ParameterXML{_name='Fecha', _value='01-03-2020'}, ParameterXML{_name='Hora', _value='12:27 PM'}," +
                " ParameterXML{_name='Lugar', _value='Valencia'}], _correo='', _telefono='0414-1779515'}";

    }
}