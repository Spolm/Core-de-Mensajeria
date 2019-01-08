package Entities.M08_Validation.XMLManagement;

import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Exceptions.UnexpectedErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Clase patrón comando que almacena el método para retornar el valor de un tag de un XML.
 */
public class CommandGetTagValue extends Command<String> {

    final static Logger log = LogManager.getLogger("CoreMensajeria");
    private String _tag;
    private Element _element;
    private Node _node;
    private String _value;

    public CommandGetTagValue(String tag, Element element){
        this._tag = tag;
        this._element = element;
    }

    /**
     * Obtiene el valor de una etiqueta o tag.
     * @throws NullValueXMLException
     * @throws UnexpectedErrorException
     */
    @Override
    public void execute() throws NullValueXMLException, UnexpectedErrorException {
        try {
            NodeList nodeList = _element.getElementsByTagName( _tag ).item(0).getChildNodes();
            _node = ( Node ) nodeList.item(0);
            _value = _node.getNodeValue();
        }catch ( NullPointerException e ){
            String msg = "Ha ocurrido una execpción: " + e.getClass().getName()
                    + "en la clase" + this.getClass().getName();
            _value = "";
            throw new NullValueXMLException( msg, e);
        }catch ( Exception e ){
            String msg = "Ha ocurrido una execpción inesperada: " + e.getClass().getName()
                    + "en la clase" + this.getClass().getName();
            log.error( msg );
            _value = "";
            throw new UnexpectedErrorException( e );
        }
    }

    /**
     * Método para devolve el valor del nodo para el manejo XML.
     * @return
     */
    @Override
    public String Return() {
        return _value;
    }

}
