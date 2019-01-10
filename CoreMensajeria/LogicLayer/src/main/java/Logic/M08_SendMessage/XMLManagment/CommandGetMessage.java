package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M08_SendMessageManager.MissLengthXMLException;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Exceptions.M08_SendMessageManager.ParameterDoesntExistsInXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Clase patrón comando que se encarga de obtener el mensaje a llenar en la plantilla.
 */
public class CommandGetMessage extends Command<Message> {

    private final static Logger log = LogManager.getLogger("CoreMensajeria");
    private Node _node;
    private Message _message;
    private Command<String> _commandGetTagValue;
    private Template _template;
    private int idMessage;
    private ArrayList<ParameterXML> _parameterXMLList;

    /**
     * Constructor de la clase CommandGetMessage que emplea
     * como parametros el nodo del archivo XML y la plantilla.
     * @param node Nodo del archivo XML
     * @param template Plantilla para comprar los parametros de la misma.
     */
    public CommandGetMessage(Node node, Template template){
        this._node = node;
        _message = new Message();
        this._template = template;
        _parameterXMLList = new ArrayList<>();
    }

    /**
     * Obtiene los mensajes para rellenar la plantilla, comparando los
     * parametros del archivo XML y el de la plantilla registrada en el sistema.
     *
     * Emplea el comando GetParameter para obtener los parametros del archivo XML.
     *
     * Compara el tamaño del mensaje dentro del archivo XML.
     *
     * @see CommandGetParameter
     * @throws MissLengthXMLException si el tamaño del archivo XML no coincide con los
     * valores registrados dentro del sistema.
     */
    @Override
    public void execute() throws MissLengthXMLException {
        try {
            if ( _node.getNodeType() == Node.ELEMENT_NODE ) {
                Element element = ( Element ) _node;
                setDestiny( element );
                NodeList nodeList = element.getElementsByTagName( "parameter" );

                idMessage = _template.get_id();
                ArrayList<Parameter> _parameterList = ParameterHandler.getParametersByMessage(idMessage);
                log.info("Se obtube el idMessage " + idMessage );
                if( nodeList.getLength() == _parameterList.size() ){
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Command<ParameterXML> _commandGetParameter = CommandsFactory.
                                createCommandGetParameter(nodeList.item(i), _parameterList);
                        _commandGetParameter.execute();
                        log.info("Se ejecutó el comando GetParameter" );
                        if(_commandGetParameter.Return() != null) {
                            _parameterXMLList.add( _commandGetParameter.Return());
                        } else {
                            _message = null;
                            log.error("falló en la ejecución del comando GetParameter en la posición " + i );
                         }
                    }
                    if(_message != null)
                        _message.set_param(_parameterXMLList);
                } else{
                    log.error( "se ha lanzado MissLengthXMLException." );
                    _message = null;
                    throw new MissLengthXMLException();
                }

            }
        } catch (MissLengthXMLException e){
            throw new MissLengthXMLException();
        } catch (ParameterDoesntExistsInXMLException e) {
            log.error( "Los parametros del mensaje " + idMessage + " no existen" );
            _message = null;
        }
        catch (Exception e) {
            log.error("Error inesperado de tipo "
                    + e.getClass().getName() );
        }
    }


    /**
     * Configura los destinos entre correo electrónico o número de telefono,
     * variando entre sí la plantilla esta configurada para enviar a ciertos canales.
     *
     * Emplea el comando GetTagValue para obtener la información del destinatario,
     * que puede ser correo electrónico o número de teléfono.
     *
     * @param element Objeto elemento para procesar el archivo XML.
     * @see CommandGetTagValue
     */

    public void setDestiny( Element element ){
        try {
            if(_template.getChannels().size() == 2) {

                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("correo", element);
                _commandGetTagValue.execute();
                _message.set_correo(_commandGetTagValue.Return());

                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("telefono", element);
                _commandGetTagValue.execute();
                _message.set_telefono(_commandGetTagValue.Return());

            } else if (_template.getChannels().size() == 1 &&
                    _template.getChannels().get(0).get_nameChannel().equalsIgnoreCase("SMS")){
                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("telefono", element);
                _commandGetTagValue.execute();
                _message.set_telefono(_commandGetTagValue.Return());
                _message.set_correo("");
            } else {
                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("correo", element);
                _commandGetTagValue.execute();
                _message.set_correo(_commandGetTagValue.Return());
                _message.set_telefono("");
            }
        } catch (NullValueXMLException e) {
            _message = null;
            log.error("Execpción, valores nulos o vacios dentro del XML." );
        } catch (Exception e){
            log.error( "Ha ocurrido una excepción inesperada." );
        }
    }

    /**
     * Retorna los parametros del mensaje a ser enviados.
     * @return mensaje para su posterior envío.
     */
    @Override
    public Message Return() {
        return _message;
    }

}
