package Logic.M08_SendMessage.XMLManagment;

import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M08_Validation.XMLManagement.ParameterXML;
import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Exceptions.M08_SendMessageManager.ParameterDoesntExistsInXMLException;
import Logic.Command;
import Logic.CommandsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Clase patrón comando que se encarga de obtener los parametros de una plantilla.
 */
public class CommandGetParameter extends Command<ParameterXML> {

    private final static Logger log = LogManager.getLogger("CoreMensajeria");
    private Node _node;
    private ParameterXML _parameterXML;
    private Command<String> _commandGetTagValue;
    private ArrayList<Parameter> _parameters;

    /**
     * Constructor de la clase CommandGetParameter que instancia un objeto
     * con el Nodo del archivo XML y la lista de parametros a ser procesados.
     * @param node Nodo del archivo XML para el procesamiento.
     * @param parameter Lista de parametros dentro del mensaje en el archivo XML.
     */
    public CommandGetParameter(Node node, ArrayList<Parameter> parameter) {
        this._node = node;
        _parameterXML = new ParameterXML();
        this._parameters = parameter;
    }

    /**
     * Busca los parametros del mensaje dentro del archivo XML,
     * guardando su contenido dentro de la variable _parameterXML para
     * su posterior retorno.
     *
     * Emplea el comando GetTagValue para obtener los valores de las etiquetas
     * dentro del mensaje en el archivo XML.
     *
     * @see CommandGetTagValue
     * @throws ParameterDoesntExistsInXMLException Si el parametro no existe
     * dentro del archivo XML
     */
    @Override
    public void execute() throws ParameterDoesntExistsInXMLException {
        try {
                if (_node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) _node;
                _commandGetTagValue = CommandsFactory.createCommandGetTagValue("name", element);
                _commandGetTagValue.execute();

                if(findParameter( _commandGetTagValue.Return())) {

                    _parameterXML.set_name(_commandGetTagValue.Return());
                    _commandGetTagValue = CommandsFactory.createCommandGetTagValue("value", element);
                    _commandGetTagValue.execute();
                    _parameterXML.set_value(_commandGetTagValue.Return());

                } else {
                    _parameterXML = null;
                    log.error( "se ha lanzado ParameterDoesntExistsXMLException," +
                            " buscando el parametro " + _commandGetTagValue.Return() );
                    throw new ParameterDoesntExistsInXMLException();
                }
            }
        } catch (NullValueXMLException e) {
            log.error("valores nulos o vacios dentro del XML");
            _parameterXML = null;
        } catch (ParameterDoesntExistsInXMLException e) {
            throw new ParameterDoesntExistsInXMLException();
        }catch (Exception e){
            log.error("Error inesperado de tipo "
                    + e.getClass().getName() );
        }
    }

    /**
     * Encuentra un parametro dentro de los posibles del archivo XML
     * @param parameter Parametro sujeto a la búsqueda.
     * @return Verdadero si encuentra el parametro dentro del mensaje,
     * falso si no lo hace.
     */
    public boolean findParameter(String parameter){
        for (Parameter param : _parameters){
            String singleParam = param.getName();
            if(singleParam.equalsIgnoreCase(parameter)){
                _parameters.remove(param);
                return true;
            }
        }
        return false;
    }

    /**
     * @return Los parametros del archivo XML.
     */
    @Override
    public ParameterXML Return() {
        return _parameterXML;
    }

}
