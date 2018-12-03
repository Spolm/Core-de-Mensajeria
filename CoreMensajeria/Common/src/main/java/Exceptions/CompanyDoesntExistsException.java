package Exceptions;

/**
 * Creado por M02 para manejo de errores en la obtencion de campañas
 */
public class CompanyDoesntExistsException extends Exception {
    public final int ERROR_CODE = 560;
    public final String ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La compañia que desea visualizar no existe.";
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
    }

    /**
     * Excepción personalizada para el manejo de errores en los metodos que buscan informacion de compañias en la base de datos
     * @param error recibe el stack trace de la exception que atrapa
     */
    public CompanyDoesntExistsException(Exception error) {
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
