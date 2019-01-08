package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.MissLengthXMLException;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase patrón comando que se encarga de procesar los XML que ha encontrado el demonio.
 */
public class CommandProcessXML extends Command<VerifiedParameter> {

    private final static Logger log = LogManager.getLogger("CoreMensajeria");
    private File _xmlFile;
    private DocumentBuilderFactory _dbFactory;
    private DocumentBuilder _dBuilder;
    private Command<Message> _commandGetMessage;
    private Command<String> _commandGetTagValue;
    private Command _commandGetTemplate;
    private Template _template;  /////// Cambiar por comando de Template
    private TemplateHandler _templateHandler = new TemplateHandler();   /////// Cambiar por comando de Template
    private String _templateId;                  /////// Cambiar por comando de Template
    private ArrayList<Message> _messageList = new ArrayList<>();
    private VerifiedParameter _verifiedParameters;

    /**
     * Constructor que instancia la clase CommandProcessXML
     * con la ruta del archivo creado.
     * @param filePath Ruta del archivo.
     */
    public CommandProcessXML(String filePath){
        _xmlFile = new File(filePath);
        _dbFactory = DocumentBuilderFactory.newInstance();
    }

    /**
     * Método para el procesa de un XML encontrado por el demonio.
     * Haciendo uso de los comando GetTagValue para obtener los valores
     * de las etiquetas dentro del archivo XML, y GetMessage para obtener
     * los parametros del mensaje a ser enviado.
     *
     * @see CommandGetTagValue
     * @see CommandGetMessage
     */
    @Override
    public void execute() {
        try {
            _dBuilder = _dbFactory.newDocumentBuilder();
            Document doc = _dBuilder.parse(_xmlFile);
            doc.getDocumentElement().normalize();

            NodeList node = doc.getElementsByTagName("template");
            _commandGetTagValue = CommandsFactory.createCommandGetTagValue("id",(Element) node.item(0));
            _commandGetTagValue.execute();
            log.info("Se ha ejecutado el comando GetTagValue" );
            _templateId = _commandGetTagValue.Return();
            log.debug("Se obtenido el TemplateId " + _templateId
                    + " del método de return del comando GetTagValue" );

            if(_templateId != "") {
                _template = _templateHandler.getTemplate(Integer.valueOf(_templateId));   /////// Cambiar por comando de Template
                log.info("Se ha ejecutado el comando DETEMPLATEQUEFALTA" ); ///*** RECORDAR CAMBIAR POR EL NOMBRE DEL COMANDO DE PLANTILLA
                NodeList nodeList = doc.getElementsByTagName("message");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    try {
                        _commandGetMessage = CommandsFactory.createCommandGetMessage(nodeList.item(i), _template);
                        _commandGetMessage.execute();
                        log.info("Se ha ejecutado el comando GetMessage para la posición " + i );
                        if (_commandGetMessage.Return() != null)
                            _messageList.add(_commandGetMessage.Return());
                    } catch (MissLengthXMLException e){
                        log.error( "El tamaño del XML es mayor a lo establecido en la plantilla, " +
                                "revise la posición " + i + " del mensaje del archivo XML." );
                    }
                }

                for (Message message : _messageList) {
                    System.out.println(message.toString());
                }

                _verifiedParameters = new VerifiedParameter();
                _verifiedParameters.set_verifiedMessages(_messageList);
                _verifiedParameters.set_template(_template);
                log.info("Se ha configurado la plantilla" );
            } else{
                log.error("El Id del temple es vacío" );
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            System.out.println("Error"); ///*** PENDIENTE POR CAMBIAR
            e1.printStackTrace();
        } catch (TemplateDoesntExistsException e) {
            log.error( "la plantilla no existe" );
        } catch (NullPointerException e){
            System.out.println("Error 3: " + e); ///*** PENDIENTE POR CAMBIAR
        } catch ( NullValueXMLException e ){
            log.error( "valores nulos o vacios dentro del XML" );
        } catch (NumberFormatException e){
            log.error( "El Id de la plantilla es inválido, solo números enteros" );
        }
        catch (Exception e){
            log.error( "Ha ocurrido una excepción inesperada" );
        }
    }

    /**
     * Retorna los parametros ya verificados.
     * @return Parametros ya verificados.
     */
    @Override
    public VerifiedParameter Return() {
        return _verifiedParameters;
    }
}
