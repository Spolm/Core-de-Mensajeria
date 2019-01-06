package Exceptions;

/**
 * Creado por M02 para manejo de errores en la obtencion de campañas
 */
public class CompanyDoesntExistsException extends PersonalizedException {
    private String _clase;
    private String _metodo;


    /**
     * Excepción personalizada para el manejo de errores en los metodos que buscan informacion de compañias en la base de datos
     * @param error recibe el stack trace de la exception que atrapa
     * @param clase recibe la clase en la que ocurrio el error
     * @param metodo recibe el metodo en el que ocurrio el error
     */
    public CompanyDoesntExistsException(Exception error, String clase, String metodo) {
        super(error);
        _clase = clase;
        _metodo = metodo;
        ERROR_CODE = 560;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La compañia que desea visualizar no existe.";
    }

    /**
     * Excepción personalizada para el manejo de errores en los metodos que buscan informacion de compañias en la base de datos
     * @param error recibe el stack trace de la exception que atrapa
     */
    public CompanyDoesntExistsException(Exception error) {
        super(error);
        ERROR_CODE = 560;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". El parametro "+" no puede ser Null";
    }



}
