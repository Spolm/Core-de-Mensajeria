package DTO.M02_DTO;

import DTO.DTO;

/**
 * Clase encargada de definir los DTO que seran utilizados por Company
 */
public class DTOFullCompany extends DTO {

    private int _idCompany;
    private String _name;
    private String _desc;
    private boolean _status;
    private String _link;
    private int _idUser;
    /**
     * DTO para Company con todos los atributos de la clase
     * @param id id para una campaña
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     * @param link
     * @param uId
     */
    public DTOFullCompany ( int id, String name, String desc, boolean status, String link, int uId ) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;
        _link = link;
        _idUser = uId;

    }
    public int get_idCompany () {
        return _idCompany;
    }

    public void set_idCompany ( int idCompany ) {
        _idCompany = idCompany;
    }

    public String get_name () {
        return _name;
    }

    public void set_name ( String name ) {
        _name = name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc ( String desc ) {
        _desc = desc;
    }

    public boolean get_status() {
        return _status;
    }

    public void set_status ( boolean status ) {
        _status = status;
    }

    public String get_link() {
        return _link;
    }

    public void set_link ( String link ) {
        _link = link;
    }

    public boolean is_status() { return _status; }

    public int get_idUser() { return _idUser; }

    public void set_idUser(int _idUser) { this._idUser = _idUser; }


    /**
     * Constructor Vacio
     */
    public DTOFullCompany() {

    }



}
