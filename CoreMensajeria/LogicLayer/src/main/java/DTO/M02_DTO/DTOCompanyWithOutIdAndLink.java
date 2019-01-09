package DTO.M02_DTO;

import DTO.DTO;

public class DTOCompanyWithOutIdAndLink extends DTO {

    private String _name;
    private String _desc;
    private boolean _status;

    /**
     * DTO para Company sin id y sin link
     * @param name nombre para una campaña recien creada
     * @param desc descripcion de la campaña
     * @param status el estatus de la campaña, puede ser activada o desactivada
     */

    public DTOCompanyWithOutIdAndLink(String name, String desc, boolean status) {

        _name = name;
        _desc = desc;
        _status = status;

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




}
