package Exceptions;

/**
 * Excepcion personalizada creada por M02 para el manejo de errores con los parametros
 */
public class ParameterCantBeNullException extends Exception {

    public final int ERROR_CODE = 561;
    public final String ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". El parametro "+" no puede ser Null";
    private String _clase;
    private String _metodo;
    private String _param;


    /**
     * excepcion personalizada para usar cuando un parametro NotNull se deje en blanco
     */
    public ParameterCantBeNullException(Exception error, String clase, String metodo) {
        super(error);
        _clase = clase;
        _metodo = metodo;
    }

    public ParameterCantBeNullException(Exception error) {
        super(error);

    }
    @Override
    public String toString()
    {
        StringBuilder str = null;


        str = new StringBuilder( ERROR_CODE + "\n" );
        str.append( ERROR_MSG + "\n" );
        str.append( super.toString() );

        return str.toString();
    }



}
