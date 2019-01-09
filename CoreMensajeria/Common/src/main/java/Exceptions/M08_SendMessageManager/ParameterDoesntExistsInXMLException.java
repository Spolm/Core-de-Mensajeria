package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepcion personalizada que se ejecuta cuando los parametros
 * del mensaje no existen
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
