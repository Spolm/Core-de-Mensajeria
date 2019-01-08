package Logic.M08_SendMessage.XMLManagment;

import Exceptions.M08_SendMessageManager.NullValueXMLException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Clase patrón comando que almacena el método para retornar el valor de un tag de un XML.
 */
public class CommandGetTagValue extends Command<String> {

    private final static Logger log = LogManager.getLogger("CoreMensajeria");
    private String _tag;
    private Element _element;
    private Node _node;
    private String _value;

    /**
     * Constructor que inicializa un objeto de la clase CommandGetTagValue
     * con la etiqueta y un objeto Element.
     * @param tag Etiqueta del archivo XML.
     * @param element Nodo del archivo XML.
     */
    public CommandGetTagValue(String tag, Element element){
        this._tag = tag;
        this._element = element;
    }

    /**
     * Obtiene el valor de una etiqueta o tag.
     * @throws NullValueXMLException si alguna etiqueta dentro
     * del XML no encuentra su valor o es ta vacio.
     * @throws UnexpectedErrorException si existe un error no esperado.
     */
    @Override
    public void execute() throws NullValueXMLException, UnexpectedErrorException {
        try {
            NodeList nodeList = _element.getElementsByTagName( _tag ).item(0).getChildNodes();
            _node = ( Node ) nodeList.item(0);
            _value = _node.getNodeValue();
            log.debug( "Se obtiene el valor [" + _value + "] del tag <" + _tag + "> " );
        }catch ( NullPointerException e ){
            String msg = "Ha ocurrido una execpción NullPointerException," +
                    " se ha lanzado NullValueXMLException" ;
            _value = "";
            log.error( msg );
            throw new NullValueXMLException( msg, e);
        }catch ( Exception e ){
            log.error( "Ha ocurrido una excepción inesperada, se ha lanzado UnexpectedErrorException" );
            _value = "";
            throw new UnexpectedErrorException( e );
        }
    }

    /**
     * Método para devolve el valor del nodo para el manejo XML.
     * @return Valor de la etiqueta en el archivo XML.
     */
    @Override
    public String Return() {
        return _value;
    }

}
