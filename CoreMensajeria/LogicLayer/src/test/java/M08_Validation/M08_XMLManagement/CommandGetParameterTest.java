package M08_Validation.M08_XMLManagement;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M08_SendMessageManager.ParameterDoesntExistsInXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandGetParameterTest {
    private static DocumentBuilderFactory _dbFactory;
    private static DocumentBuilder dBuilder;
    private static NodeList node;
    private static Document doc;
    private static Element element;
    private static Command<ParameterXML> commandGetParameter;
    private static ArrayList<Parameter> param = new ArrayList<>();

    @Test
    public void GetParameterNotNullTest(){
        try {
            initRouteFile("prueba5.xml");
            commandGetParameter.execute();
            ParameterXML parameter = commandGetParameter.Return();
            assertNotNull(parameter);
            assertEquals("Prueba",parameter.get_name());
            assertEquals("This is a test",parameter.get_value());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionCommandProcessXML(){
        assertThrows(ParameterDoesntExistsInXMLException.class, () -> {
            initRouteFile("prueba6.xml");
            commandGetParameter.execute();
        });
    }

    private static void initRouteFile(String xml){
        param.add(new Parameter("Prueba"));
        ClassLoader classLoader = new CommandGetParameterTest().getClass().getClassLoader();
        File _file1 = new File(classLoader.getResource("xml/"+xml).getFile());
        _dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dBuilder = _dbFactory.newDocumentBuilder();
            doc = dBuilder.parse( _file1.getPath() );
            doc.getDocumentElement().normalize();
            node = doc.getElementsByTagName( "template" );
            element = (Element) node.item(0);
            commandGetParameter = CommandsFactory.createCommandGetParameter(element,param);

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}