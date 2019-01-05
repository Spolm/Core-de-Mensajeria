package DTO.M02_DTO;

public class DTOCompanyWithOut_Link {

    private int _idCompany;
    private String _name;
    private String _desc;
    private boolean _status;


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



    /**
     * DTO para Company con id sin link
     * @param id id para una campaña
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     */

    public DTOCompanyWithOut_Link(int id, String name, String desc, boolean status) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;

    }

}
