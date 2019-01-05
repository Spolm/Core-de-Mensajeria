package DTO.M03_DTO;

public class DTOIdStatusCampaign {

    private int _idCampaign;
    private boolean _status;

    /**
     * Constructor Vacio
     */
    public DTOIdStatusCampaign() {

    }

    /**
     * DTO para Company con todos los atributos de la clase
     * @param id id para una campaña
     * @param status el estatus de una campaña
     */
    public DTOIdStatusCampaign ( int id, boolean status ) {

        _idCampaign = id;
        _status = status;

    }

    public int get_idCampaign() {
        return _idCampaign;
    }

    public void set_idCampaign( int _idCampaign )  {
        this._idCampaign = _idCampaign;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status( boolean _status ) {
        this._status = _status;
    }
}
