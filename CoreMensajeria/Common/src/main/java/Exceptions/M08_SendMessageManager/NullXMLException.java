package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada, el archivo XML es nulo o esta vacio.
 */
public class NullXMLException extends PersonalizedException {

    public NullXMLException(String message, Exception e) {
        super(message, e);
    }
}
