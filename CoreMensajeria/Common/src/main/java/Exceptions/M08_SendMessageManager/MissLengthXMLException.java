package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada cuando el tamaño del XML no
 * coincide con lo registrado en el sistema.
 */
public class MissLengthXMLException extends PersonalizedException {

    public MissLengthXMLException() { super(); }

}
