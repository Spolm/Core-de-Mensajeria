package DTO.M03_DTO;

public class DTOIdStatusCampaign {

    private int _idCampaign;
    private boolean _statusCampaign;

    /**
     * Constructor Vacio
     */
    public DTOIdStatusCampaign() {

    }

    /**
     * DTO para Campaign con el id y el estatus
     * @param id id para una campaña
     * @param status el estatus de una campaña
     */
    public DTOIdStatusCampaign ( int id, boolean status ) {

        _idCampaign = id;
        _statusCampaign = status;

    }

    public int get_idCampaign() {
        return _idCampaign;
    }

    public void set_idCampaign( int _idCampaign )  {
        this._idCampaign = _idCampaign;
    }

    public boolean is_status() {
        return _statusCampaign;
    }

    public void set_status( boolean _status ) {
        this._statusCampaign = _status;
    }
}
