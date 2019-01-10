package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada que se ejecuta cuando algun parametro
 * en el documento XML es nulo.
 */
public class NullValueXMLException extends PersonalizedException {

    /**
     * Constructor simple que recibe el mensaje y la excepcion
     *
     * @param message mensaje personalizado
     * @param e       con informacion específica sobre la excepcion que
     *                fue ejecutada
     */
    public NullValueXMLException(String message, Exception e) {
        super(message, e);
    }
}
