package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada, algún valor del XML es nulo
 */
public class NullValueXMLException extends PersonalizedException {

    /**
     * Constructor simple que sube el mensaje y la excepción a la clase padre.
     */
    public NullValueXMLException(String message, Exception e) {
        super( message, e );
    }
}
