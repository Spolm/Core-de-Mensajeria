package DTO.M02_DTO;
import DTO.DTO;


public class DTOIdCompUser extends DTO {
    private int _idCompany;
    private int _idUser;


    /**
     * Constructor Vacio
     */
    public DTOIdCompUser() {

    }

    /**
     * DTO para Company con todos los atributos de la clase
     * @param id id para una compañia
     * @param user el ide del usuario
     */
    public DTOIdCompUser ( int id, int user ) {

        _idCompany = id;
        _idUser = user;

    }

    public int get_idCompany() {
        return _idCompany;
    }

    public void set_idCompany(int _idCompany) {
        this._idCompany = _idCompany;
    }

    public int get_idUser() { return _idUser; }

    public void set_idUser(int _idUser) { this._idUser = _idUser; }
}

