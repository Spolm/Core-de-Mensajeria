package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada que se ejecuta cuando el
 * documento XML es nulo o esta vacio
 *
 * @see PersonalizedException
 */
public class NullXMLException extends PersonalizedException {

    /**
     * Constructor Vacio
     */
    public NullXMLException() {
        super();
    }

    /**
     * Constructor simple que recibe el mensaje y la excepcion
     *
     * @param message mensaje personalizado
     * @param e       con informacion específica sobre la excepcion que
     *                fue ejecutada
     */
    public NullXMLException(String message, Exception e) {
        super(message, e);
    }
}
