package Exceptions;

public class CompanyDoesntExistsException extends Exception {
    public final int ERROR_CODE = 560;
    public final String ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La compañia que desea visualizar no existe.";
    private String _clase;
    private String _metodo;


    /**
     * excepcion personalizada para Getcampaings
     */
    public CompanyDoesntExistsException(Exception error, String clase, String metodo) {
        super(error);
        _clase = clase;
        _metodo = metodo;
    }

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
