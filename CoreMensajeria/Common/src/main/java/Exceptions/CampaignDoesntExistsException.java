package Exceptions;

public class CampaignDoesntExistsException extends Exception {
    public final int ERROR_CODE = 550;
    public final String ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La campaña no existe.";
    private String _clase;
    private String _metodo;


    /**
     * excepcion personalizada para Getcampaings
     */
    public CampaignDoesntExistsException(Exception error, String clase, String metodo) {
        super(error);
        _clase = clase;
        _metodo = metodo;
    }

    public CampaignDoesntExistsException(Exception error) {
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
