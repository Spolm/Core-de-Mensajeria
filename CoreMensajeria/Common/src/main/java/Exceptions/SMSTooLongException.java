package Exceptions;

/**
 * Excepción personalizada para manejar el tamaño del sms.
 */
public class SMSTooLongException extends Exception {
    public final int ERROR_CODE = 562;
    public final String  ERROR_MSG = "Ha ocurrido un errror "+ERROR_CODE+" SMS supera 160 caracteres";

    /**
     * Constructor vacío.
     */
    public SMSTooLongException(){}

    /**
     * @param e recibe la excepción que se esta encapsulando.
     */
    public SMSTooLongException(Exception e) {
        super(e);

    }

    /**
     * @return un string con el código y mensaje de error junto con el error original.
     */
    @Override
    public String toString(){
        StringBuilder str = null;


        str = new StringBuilder( ERROR_CODE + "\n" );
        str.append( ERROR_MSG + "\n" );
        str.append( super.toString() );

        return str.toString();
    }

}
