package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Message;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M08_SendMessageManager.MissLengthXMLException;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Exceptions.ParameterDoesntExistsException;
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

    public CommandGetMessage(Node node, Template template){
        this._node = node;
        _message = new Message();
        this._template = template;
        _parameterXMLList = new ArrayList<>();
    }

    /**
     * Obtiene los mensajes para rellenar la plantilla.
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
                log.info("Se ejecutó el comando FALTANOMBRE " +
                        " con el idMessage " + idMessage ); ///*** FALTA CAMBIAR POR EL NOMBRE DEL COMANDO
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
        } catch (ParameterDoesntExistsException e) {
            log.error( "Los parametros del mensaje " + idMessage + " no existen" ); ///*** MOSCA CON LOS CAMBIOS
            _message = null;
        } catch (MissLengthXMLException e){
            throw new MissLengthXMLException();
        } catch (Exception e) {
            log.error( "Ha ocurrido una excepción inesperada." );
        }
    }

    /**
     * Devuelve la lista de parametros para rellenar la plantilla.
     * @return
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
                    _template.getChannels().get(0).getNameChannel().equalsIgnoreCase("SMS")){
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

    @Override
    public Message Return() {
        return _message;
    }

}
