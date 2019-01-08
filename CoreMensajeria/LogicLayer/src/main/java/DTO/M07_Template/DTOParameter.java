package DTO.M07_Template;

import DTO.DTO;

public class DTOParameter extends DTO {

    /**
     * id of the parameter
     */
    private int pid;
    /**
     * name of the parameter
     */
    private String _pName;
    /**
     * description of the parameter
     */
    private String _pDescription;

    public DTOParameter() {
    }

    public DTOParameter(int pid, String _pName, String pdescription) {
        this.pid = pid;
        this._pName = _pName;
        this._pDescription = pdescription;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String get_pName() {
        return _pName;
    }

    public void set_pName(String _pName) {
        this._pName = _pName;
    }

    public String get_pDescription() {
        return _pDescription;
    }

    public void set_pDescription(String _pDescription) {
        this._pDescription = _pDescription;
    }
}
