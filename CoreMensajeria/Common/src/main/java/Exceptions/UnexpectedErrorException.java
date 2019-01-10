package Exceptions;

public class UnexpectedErrorException extends PersonalizedException{


    /**
     * Constructor vacío.
     */
    public UnexpectedErrorException(){
        ERROR_CODE = 570;
        ERROR_MSG = "Ha ocurrido un errror Inesperado"+ERROR_CODE;
    }

    /**
     * @param e recibe la excepción que se esta encapsulando.
     */
    public UnexpectedErrorException(Exception e) {
        super(e);
        ERROR_CODE = 570;
        ERROR_MSG = "Ha ocurrido un errror Inesperado"+ERROR_CODE;

    }

    /**
     * @return un string con el código y mensaje de error junto con el error original.
     */

}
