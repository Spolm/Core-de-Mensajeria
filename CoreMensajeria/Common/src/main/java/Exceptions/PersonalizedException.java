package Exceptions;

public class PersonalizedException extends Exception {
    public  int ERROR_CODE ;
    public  String ERROR_MSG ;

    public PersonalizedException() {
    }

    public PersonalizedException(Exception e) {
        super(e);
    }

    public PersonalizedException(String message, Exception e) {
        super(message, e);
    }

    public PersonalizedException(String message) {
        super(message);
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
