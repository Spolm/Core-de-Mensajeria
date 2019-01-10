package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada que se ejecuta cuando
 * el mensaje es muy largo
 *
 * @see PersonalizedException
 */
public class SMSTooLongException extends PersonalizedException {

    /**
     * Constructor vacío.
     */
    public SMSTooLongException() {
        ERROR_CODE = 562;
        ERROR_MSG = "Ha ocurrido un errror " + ERROR_CODE + " SMS supera 160 caracteres";
    }

    /**
     * Constructor que recibe la Excepcion por parametro que nos
     * permite dar mas informacion sobre la excepcion
     *
     * @param e recibe la excepción que se esta encapsulando.
     */
    public SMSTooLongException(Exception e) {
        super(e);
        ERROR_CODE = 562;
        ERROR_MSG = "Ha ocurrido un errror " + ERROR_CODE + " SMS supera 160 caracteres";

    }

    /**
     * @return un string con el código y mensaje de error junto con el error original.
     */


}
