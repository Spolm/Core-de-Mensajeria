package DTO.M02_DTO;

import DTO.DTO;

public class DTOIdCompany extends DTO {
    private int _idCompany;

    public int get_idCompany() {
        return _idCompany;
    }

    public void set_idCompany(int _idCompany) {
        this._idCompany = _idCompany;
    }

    /**
     * Constructor Vacio
     */
    public DTOIdCompany() {

    }

    /**
     * DTO para Company con todos los atributos de la clase
     * @param id id para una campaña
     */
    public DTOIdCompany ( int id) {

        _idCompany = id;

    }


}


