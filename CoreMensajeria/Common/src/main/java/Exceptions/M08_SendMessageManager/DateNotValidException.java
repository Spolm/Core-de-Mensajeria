package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepcion Personalizada que se ejecuta cuando el
 * formato de la fecha es invalido
 *
 * @see PersonalizedException
 */
public class DateNotValidException extends PersonalizedException {

    /**
     * Constructor sin parametros
     */
    public DateNotValidException() {
    }

    /**
     * Constructor que recibe la excepcion por parametros
     */
    public DateNotValidException(Exception e) {
        super(e);
    }

    /**
     * Constructor que recibe el mensaje por parametro
     * @param msg mensaje con el que se ejecutara la excepcion
     */
    public DateNotValidException(String msg) {
        super(msg);
    }

    /**
     * Constructor que recibe el mensaje y la excepcion por parametro
     * @param msg mensaje con el que se ejecutara la excepcion
     * @param e informacion mas específica sobre la excepcion que fue ejecutada
     */

    public DateNotValidException(String msg, Exception e) {
        super(msg, e);
    }
}
