package M08_Validation.M08_XMLManagement;

import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas unitarias para la clase del comando GetTagVaule
 */
class CommandGetTagValueTest {
    private static NodeList node1;
    private static Document doc1;

    /**
     * Configuración inicial de todos los archivos a ser probados,
     * solo el prueba1.xml tiene todos los parametros correctos.
     */
    @BeforeAll
    @DisplayName( "Configuración inicial para todas las pruebas del comando GetTagValue" )
    static void settingUp() {
        ClassLoader classLoader = new CommandGetMessageTest().getClass().getClassLoader();
        File _file1 = new File(classLoader.getResource("xml/prueba1.xml").getFile());
        DocumentBuilderFactory _dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = _dbFactory.newDocumentBuilder();
            doc1 = dBuilder.parse( _file1.getPath() );
            doc1.getDocumentElement().normalize();
            node1 = doc1.getElementsByTagName( "template" );
        } catch ( Exception e ) {
            Assert.fail( e.getMessage()
            + "[Error en el BeforeAll]" );
        }
    }

    /**
     * Probando escenario exitoso del comando GetTagValue
     */
    @Test
    @DisplayName( "Inicia prueba de escenario exitoso del comando GetTagValue" )
    void executeTest() {
        try {
            Command commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("id", (Element) node1.item(0) );
            commandGetTagValue.execute();
            assertEquals( "5", commandGetTagValue.Return() );
        } catch ( Exception e ) {
            Assert.fail( e.getMessage() );
        }
    }

    /**
     * Probando la excepción personalizada NullValueXMLException lanzada por
     * la búsqueda de una etiqueta vacia.
     *
     * @see NullValueXMLException
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada NullValueXMLExceptionTest con etiqueta vacia" )
    void executeNullValueXMLExceptionTestEmptyTag(){
       assertThrows( NullValueXMLException.class, () -> {
           Command commandGetTagValue =
                   CommandsFactory.createCommandGetTagValue(" ", (Element) node1.item(0) );
           commandGetTagValue.execute();
        });
    }

    /**
     * Probando la excepción personalizada NullValueXMLException
     * cuando busca una etiqueta principal vacia en el XML .
     *
     * @see NullValueXMLException
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada " +
            "NullValueXMLExceptionTest con etiqueta principal vacia" )
    void executeNullValueXMLExceptionTestEmptyMainTag(){
        assertThrows( NullValueXMLException.class, () -> {
            NodeList nodeTest = doc1.getElementsByTagName(" ");
            Command commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("id", (Element) nodeTest.item(0) );
            commandGetTagValue.execute();
        });
    }

    /**
     * Probando la excepción personalizada NullValueXMLException lanzada por
     * la búsqueda de una etiqueta que no existe dentro del archivo XML.
     *
     * @see NullValueXMLException
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada NullValueXMLExceptionTest" +
            "con etiqueta que no existe en el archivo XML" )
    void executeNullValueXMLExceptionTestBadFillTag(){
        assertThrows( NullValueXMLException.class, () -> {
            Command commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("XXX", (Element) node1.item(0) );
            commandGetTagValue.execute();
        });
    }

    /**
     * Probando la excepción personalizada NullValueXMLException
     * cuando busca una etiqueta principal en el XML que no existe.
     *
     * @see NullValueXMLException
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada " +
            "NullValueXMLExceptionTest con etiqueta principal incorrecta" )
    void executeNullValueXMLExceptionTestBadMainTag(){
        assertThrows( NullValueXMLException.class, () -> {
            NodeList nodeTest = doc1.getElementsByTagName("XXX");
            Command commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("id", (Element) nodeTest.item(0) );
            commandGetTagValue.execute();
        });
    }

    /**
     * Probando la excepción personalizada NullValueXMLException motivada
     * por un nodo Nulo pasado al comando
     *
     * @see NullValueXMLException
     */
    @Test
    @DisplayName( "Inicia prueba de excepción personalizada NullValueXMLExceptionTest" +
            "con parametro del nodo nulo hacia el comando" )
    void executeNullValueXMLExceptionTestNullNode(){
        assertThrows( NullValueXMLException.class, () -> {
            Command commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("id", null );
            commandGetTagValue.execute();
        });
    }
}