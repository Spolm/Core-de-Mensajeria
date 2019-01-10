package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepcion personalizada que se ejecuta cuando
 * el parametro que se recibe en el XML no concuerda
 * con los parametros de la plantilla.
 *
 * @see PersonalizedException
 */
public class ParameterDoesntExistsInXMLException extends PersonalizedException {

    /**
     * Constructor vacio
     */
    public ParameterDoesntExistsInXMLException() {
        super();
    }

}
