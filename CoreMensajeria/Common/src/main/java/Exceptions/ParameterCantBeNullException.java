package Exceptions;

/**
 * Excepcion personalizada creada por M02 para el manejo de errores con los parametros
 */
public class ParameterCantBeNullException extends PersonalizedException {
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
        ERROR_CODE = 561;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". El parametro "+" no puede ser Null";
    }

    public ParameterCantBeNullException(Exception error) {
        super(error);
        ERROR_CODE = 561;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". El parametro "+" no puede ser Null";

    }

    public ParameterCantBeNullException() {
        super();
    }




}
