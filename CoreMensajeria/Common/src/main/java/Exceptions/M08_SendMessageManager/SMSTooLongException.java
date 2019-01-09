package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada para manejar el tamaño del sms.
 */
public class SMSTooLongException extends PersonalizedException {

    /**
     * Constructor vacío.
     */
    public SMSTooLongException(){
        ERROR_CODE = 562;
        ERROR_MSG = "Ha ocurrido un errror "+ERROR_CODE+" SMS supera 160 caracteres";
    }

    /**
     * @param e recibe la excepción que se esta encapsulando.
     */
    public SMSTooLongException(Exception e) {
        super(e);
        ERROR_CODE = 562;
        ERROR_MSG = "Ha ocurrido un errror "+ERROR_CODE+" SMS supera 160 caracteres";

    }

    /**
     * @return un string con el código y mensaje de error junto con el error original.
     */


}
