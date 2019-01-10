package M08_Validation.M08_XMLManagement;

import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Exceptions.M08_SendMessageManager.MissLengthXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas unitarias para la clase del comando GetMessageTest
 */
class CommandGetMessageTest {
    private static NodeList node1;
    private static NodeList node2;
    private static Template _template;
    private Message _message;

    /**
     * Configuración inicial de todos los archivos a ser probados,
     * solo el prueba1.xml tiene todos los parametros correctos.
     */
    @BeforeAll
    @DisplayName( "Configuración inicial del comando GetMessage" )
    static void settingUp() {
        Command<Template> _commandGetTemplate = CommandsFactory.createCommandGetTemplate(5);
        ClassLoader classLoader = new CommandGetMessageTest().getClass().getClassLoader();
        File _file1 = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        File _file2 = new File(classLoader.getResource("xml/prueba2.xml").getFile());
        DocumentBuilderFactory _dbFactory = DocumentBuilderFactory.newInstance();
        try {
            _commandGetTemplate.execute();
            _template = _commandGetTemplate.Return();
            DocumentBuilder dBuilder = _dbFactory.newDocumentBuilder();
            Document doc1 = dBuilder.parse(_file1.getPath());
            doc1.getDocumentElement().normalize();
            Document doc2 = dBuilder.parse(_file2.getPath());
            doc2.getDocumentElement().normalize();
            node1 = doc1.getElementsByTagName("message");
            node2 = doc2.getElementsByTagName("message");
        } catch (Exception e) {
            Assert.fail( e.getMessage()
                    + "[Error en el BeforeAll]" );
        }

    }

    /**
     * Limpiando/Inicializando los valores del objeto mensaje.
     */
    @BeforeEach
    void cleanValues(){
        _message = null;
    }

    /**
     * Método para probar el escenario extioso del comando GetMessageTest
     */
    @Test
    @DisplayName( "Inicio del escenario exitoso del comando GetMessageTest" )
    void executeTest(){

        Command<Message> _commandGetMessage =
                CommandsFactory.createCommandGetMessage(node1.item(0), _template);
        try {
            _commandGetMessage.execute();
            _message = _commandGetMessage.Return();
            if ( _message == null )
                Assert.fail( "El valor del mensaje es nulo y/o vacio." + _commandGetMessage.Return() );
            assertEquals( "Carlos@gmail.com" , _message.get_correo() );
            assertEquals( "FECHA Inicio" , _message.get_param().get(0).get_name() );
            assertEquals( "12-12-2019" , _message.get_param().get(0).get_value() );
            assertEquals( "Fecha Fin" , _message.get_param().get(1).get_name() );
            assertEquals( "01-03-2020" , _message.get_param().get(1).get_value() );
        } catch (Exception e) {
            Assert.fail( e.getMessage()
                    + "[Error en el BeforeAll]" );
        }
    }

    /**
     * Probando la excepción personalizada MissLengthXMLException
     * en caso de que los parametros del mensaje sea menor que el esperado.
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada MissLengthXMLException" +
            " con un set de parametros es menor al esperado" )
    void executeMissLengthXMLExceptionTestLess(){
        assertThrows( MissLengthXMLException.class, () -> {
            Command<Message> _commandGetMessage =
                    CommandsFactory.createCommandGetMessage(node2.item(2), _template);
            _commandGetMessage.execute();
        });
    }

    /**
     * Probando la excepción personalizada MissLengthXMLException
     * en caso de que los parametros del mensaje sea mayor que el esperado.
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada MissLengthXMLException" +
            " con un set de parametros es mayor al esperado" )
    void executeMissLengthXMLExceptionTestMore(){
        assertThrows( MissLengthXMLException.class, () -> {
            Command<Message> _commandGetMessage =
                    CommandsFactory.createCommandGetMessage(node2.item(1), _template);
            _commandGetMessage.execute();
        });
    }

}