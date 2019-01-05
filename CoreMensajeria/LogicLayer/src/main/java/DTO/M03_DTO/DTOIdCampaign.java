package DTO.M03_DTO;

import DTO.DTO;

public class DTOIdCampaign  extends DTO {

    private int _idCampaign;

    /**
     * Constructor Vacio
     */
    public DTOIdCampaign() {

    }

    /**
     * DTO para Company con todos los atributos de la clase
     *
     * @param id id para una campaña
     */
    public DTOIdCampaign(int id) {

        _idCampaign = id;

    }

    public int get_idCampaign() {
        return _idCampaign;
    }

    public void set_idCampaign( int _idCampaign ) {
        this._idCampaign = _idCampaign;
    }


}