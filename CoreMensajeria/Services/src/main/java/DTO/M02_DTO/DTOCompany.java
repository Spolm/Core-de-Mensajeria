package DTO.M02_DTO;

import DTO.DTO;

/**
 * Clase encargada de definir los DTO que seran utilizados por Company
 */
public class DTOCompany {

    private int _idCompany;
    private String _name;
    private String _desc;
    private boolean _status;
    private String _link;

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


    /**
     * Constructor Vacio
     */
    public DTOCompany() {

    }

    /**
     * Constructor para Company sin id
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     */

    public DTOCompany(String name, String desc, boolean status) {

        _name = name;
        _desc = desc;
        _status = status;

    }

    /**
     * Constructor para Company con id
     * @param id id para una campaña
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     */

    public DTOCompany(int id, String name, String desc, boolean status) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;

    }


    /**
     * Constructor para Company con id
     * @param id id para una campaña
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     * @param link
     */
    public DTOCompany ( int id, String name, String desc, boolean status, String link ) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;
        _link = link;

    }
}
