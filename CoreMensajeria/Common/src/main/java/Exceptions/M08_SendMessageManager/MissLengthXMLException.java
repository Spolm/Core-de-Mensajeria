package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepcion que se ejecuta cuando la cantidad de parametros
 * que hay en el DocumentoXML no coincide con la cantidad de
 * parametros que tiene la plantilla
 * @see PersonalizedException
 */
public class MissLengthXMLException extends PersonalizedException {

    /**
     * Constructor vacio de MissLengthXMLException */
    public MissLengthXMLException() {
        super();
    }

}
