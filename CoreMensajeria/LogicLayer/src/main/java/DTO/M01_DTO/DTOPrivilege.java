package DTO.M01_DTO;

import java.sql.Date;
import DTO.DTO;

public class DTOPrivilege extends DTO {

    private int _idPrivileges;
    private String _codePrivileges;
    private String _actionPrivileges;

    public DTOPrivilege (int _idPrivileges, String _codePrivileges, String _actionPrivileges) {
        this._idPrivileges = _idPrivileges;
        this._codePrivileges = _codePrivileges;
        this._actionPrivileges = _actionPrivileges;
    }

    public int get_idPrivileges() {
        return _idPrivileges;
    }

    public void set_idPrivileges(int _idPrivileges) {
        this._idPrivileges = _idPrivileges;
    }

    public String get_codePrivileges() {
        return _codePrivileges;
    }

    public void set_codePrivileges(String _codePrivileges) {
        this._codePrivileges = _codePrivileges;
    }

    public String get_actionPrivileges() {
        return _actionPrivileges;
    }

    public void set_actionPrivileges(String _actionPrivileges) {
        this._actionPrivileges = _actionPrivileges;
    }

    public DTOPrivilege() {
    }
}
