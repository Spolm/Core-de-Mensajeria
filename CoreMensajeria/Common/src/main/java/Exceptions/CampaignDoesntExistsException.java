package Exceptions;

public class CampaignDoesntExistsException extends PersonalizedException {
    private String _clase;
    private String _metodo;


    /**
     * excepcion personalizada para Getcampaings
     */
    public CampaignDoesntExistsException(Exception error, String clase, String metodo) {
        super(error);
        _clase = clase;
        _metodo = metodo;
        ERROR_CODE = 550;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La campaña no existe.";
    }

    public CampaignDoesntExistsException(Exception error) {
        super(error);
        ERROR_CODE = 550;
        ERROR_MSG = "Ha ocurrido un error "+ ERROR_CODE+". La campaña no existe.";
    }

}
