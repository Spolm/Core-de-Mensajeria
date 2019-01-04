package Entities.M02_Company;

import Entities.Entity;

public class Company extends Entity {
    private int _idCompany;
    private int _idUser;
    private String _name;
    private String _desc;
    private boolean _status;
    private String _link;
            private boolean _habilitado;    // Anadido por la estructura del mapper

    public int get_idCompany () {
        return _idCompany;
    }

    public void set_idCompany ( int idCompany ) {
        this._idCompany = idCompany;
    }


    public int get_idUser () {
        return _idUser;
    }

    public void set_idUser ( int idUser ) {
        this._idUser = idUser;
    }


    public String get_name () {
        return _name;
    }

    public void set_name ( String name ) {
        this._name = name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc ( String desc ) {
        this._desc = desc;
    }

    public boolean get_status() {
        return _status;
    }

    public void set_status ( boolean status ) {
        this._status = status;
    }

    public String get_link() {
        return _link;
    }

    public void set_link ( String link ) {
        this._link = link;
    }

    public boolean get_habilitado() { return _habilitado; }

    public void set_habilitado(boolean _habilitado) { this._habilitado = _habilitado; }


    public Company() {
        /**
         * Constructor Vacio
         */
    }


    /**
     * Constructor para Companias sin _id ni _link
     * @param name nombre para una compania recien creada
     * @param desc descripcion de la compania
     * @param status el estatus de la compania, puede ser activada o desactivada
     */

    public Company(String name, String desc, boolean status) {

        _name = name;
        _desc = desc;
        _status = status;

    }

    /**
     * Constructor para Companias sin _link
     * @param id id de la compania
     * @param status el estatus de la compania, puede ser activada o desactivada
     */
    public Company(int id, boolean status) {

        _idCompany = id;
        _status = status;

    }
    /**
     * Constructor para Companias sin _link
     * @param id id de la compania
     * @param name nombre para una compania recien creada
     * @param desc descripcion de la compania
     * @param status el estatus de la compania, puede ser activada o desactivada
     */
    public Company(int id, String name, String desc, boolean status) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;

    }


    /**
     * Constructor para Companias
     * @param id id de la compania
     * @param name nombre para una compania recien creada
     * @param desc descripcion de la compania
     * @param status el estatus de la compania, puede ser activada o desactivada
     * @param link
     */
    public Company ( int id, String name, String desc, boolean status, String link, int userId ) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;
        _link = link;
        _idUser = userId;

    }


    /**
     * Constructor para Companias
     * @param id id de la compania
     */
    public Company (int id){
        _idCompany =  id;
    }

}
