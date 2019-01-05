package DTO.M02_DTO;
import DTO.DTO;


public class DTOIdStatusCompany extends DTO {
    private int _idCompany;
    private boolean _status;


    /**
     * Constructor Vacio
     */
    public DTOIdStatusCompany() {

    }

    /**
     * DTO para Company con todos los atributos de la clase
     * @param id id para una compañia
     * @param status el estatus de una compañia
     */
    public DTOIdStatusCompany ( int id, boolean status ) {

        _idCompany = id;
        _status = status;

    }

    public int get_idCompany() {
        return _idCompany;
    }

    public void set_idCompany(int _idCompany) {
        this._idCompany = _idCompany;
    }

    public boolean is_status() { return _status; }

    public void set_status(boolean _status) { this._status = _status; }
}
