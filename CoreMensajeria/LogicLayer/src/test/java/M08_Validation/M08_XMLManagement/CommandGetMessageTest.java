package M08_Validation.M08_XMLManagement;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandGetMessageTest {

    private static File _file;
    private static Command<VerifiedParameter> _commandProcessXML;
    private VerifiedParameter verifiedParameter;
    private String prueba, prueba2, prueba3;
    private String _text;

    @BeforeEach
    public void init(){
        ClassLoader classLoader = getClass().getClassLoader();
        _file = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        _text = _file.getPath();
        _text = _text.replaceAll("%20"," ");
        _commandProcessXML = CommandsFactory.createCommandProcessXML(_text);
        initPruebas();
    }

    @Test
    public void testCommandProcessXML(){
        try {
            System.out.println(_text);
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

    public void initPruebas(){
        prueba = "Message{_param=[ParameterXML{_name='FECHA Inicio', _value='12-12-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='01-03-2020'}], _correo='Carlos@gmail.com', _telefono=''}";
        prueba2 = "Message{_param=[ParameterXML{_name='Fecha Inicio', _value='05-12-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='02-04-2020'}], _correo='Carlos@gmail.com', _telefono=''}";
        prueba3 = "Message{_param=[ParameterXML{_name='Fecha Inicio', _value='11-14-2019'}, " +
                "ParameterXML{_name='Fecha Fin', _value='12-28-28'}], _correo='Sabrina@gmail.com', _telefono=''}";
    }
}