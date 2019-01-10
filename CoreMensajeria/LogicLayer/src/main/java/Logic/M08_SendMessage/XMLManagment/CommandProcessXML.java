package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.*;
import Exceptions.M08_SendMessageManager.TemplateNotApprovedException;
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
     *
     * @throws NullXMLException Si el XML esta vacio.
     * @throws TemplateNotApprovedException la plantilla no esta aprobada.
     */
    @Override
    public void execute() throws NullXMLException, TemplateNotApprovedException {
        try {
            DocumentBuilder _dBuilder = _dbFactory.newDocumentBuilder();
            Document doc = _dBuilder.parse(_xmlFile);
            doc.getDocumentElement().normalize();

            NodeList node = doc.getElementsByTagName("template");
            Command<String> _commandGetTagValue =
                    CommandsFactory.createCommandGetTagValue("id", (Element) node.item(0));
            _commandGetTagValue.execute();
            log.info("Se ha ejecutado el comando GetTagValue" );
            String _templateId = _commandGetTagValue.Return();
            log.debug("Se obtenido el TemplateId " + _templateId
                    + " del método de return del comando GetTagValue" );

            if(_templateId != "") {
                int idTemplate = Integer.valueOf(_templateId);
                Command<Boolean> commandValidateTemplate =
                        CommandsFactory.createCommandValidateTemplate(idTemplate);
                commandValidateTemplate.execute();

                Command<Template> _commandGetTemplate =
                        CommandsFactory.createCommandGetTemplate(idTemplate);
                _commandGetTemplate.execute();
                Template _template = _commandGetTemplate.Return();
                log.info("Se ha ejecutado el comando GetTemplate" );
                NodeList nodeList = doc.getElementsByTagName("message");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    try {
                        Command<Message> _commandGetMessage =
                                CommandsFactory.createCommandGetMessage(nodeList.item(i), _template);
                        _commandGetMessage.execute();
                        log.info("Se ha ejecutado el comando GetMessage para la posición " + i );
                        if (_commandGetMessage.Return() != null)
                            _messageList.add(_commandGetMessage.Return());
                    } catch (MissLengthXMLException e){
                        log.error( "El tamaño del XML es mayor a lo establecido en la plantilla, " +
                                "revise la posición " + i + " del mensaje del archivo XML." );
                    }
                }

                _verifiedParameters = new VerifiedParameter( _messageList, _template );
                log.info("Se ha configurado la plantilla" );

            } else{
                log.error("El Id del temple es vacío" );
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            String msg = "El archivo XML es vacio o inválido.";
            _verifiedParameters = null;
            log.error( "Se ha lanzado NullXMLException, " + msg );
            throw new NullXMLException( msg, e);
        } catch ( TemplateDoesntExistsException e ) {
            _verifiedParameters = null;
            log.error( "la plantilla no existe" );
        } catch ( NullValueXMLException e ){
            _verifiedParameters = null;
            log.error( "valores nulos o vacios dentro del XML" );
            String msg = "El archivo XML es inválido.";
            throw new NullXMLException( msg, e);
        } catch ( NumberFormatException e ){
            _verifiedParameters = null;
            log.error( "El Id de la plantilla es inválido, solo números enteros" );
        }catch (TemplateNotApprovedException e) {
            _verifiedParameters = null;
            log.error( e.getMessage() );
            throw new TemplateNotApprovedException();
        }catch (Exception e){
            log.error("Error inesperado de tipo "
                    + e.getClass().getName() );
            _verifiedParameters = null;
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
