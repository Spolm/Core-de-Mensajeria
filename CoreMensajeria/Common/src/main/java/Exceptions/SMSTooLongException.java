package Exceptions;

public class SMSTooLongException extends Exception {
    public final int ERROR_CODE = 562;
    public final String  ERROR_MSG = "Ha ocurrido un errror "+ERROR_CODE+" SMS supera 160 caracteres";

    public SMSTooLongException(){}

    public SMSTooLongException(Exception e) {
        super(e);

    }

    @Override
    public String toString(){
        StringBuilder str = null;


        str = new StringBuilder( ERROR_CODE + "\n" );
        str.append( ERROR_MSG + "\n" );
        str.append( super.toString() );

        return str.toString();
    }

}
