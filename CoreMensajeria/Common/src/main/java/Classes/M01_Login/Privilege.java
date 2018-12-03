package Classes.M01_Login;

import java.util.Objects;

public class Privilege {

    private int _idPrivileges;
    private String _codePrivileges;
    private String _actionPrivileges;

    public Privilege() {
    }

    public Privilege(int _idPrivileges, String _codePrivileges, String _actionPrivileges) {
        this._idPrivileges = _idPrivileges;
        this._codePrivileges = _codePrivileges;
        this._actionPrivileges = _actionPrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;

        return _idPrivileges == privilege._idPrivileges
                && Objects.equals(_codePrivileges, privilege._codePrivileges)
                && Objects.equals(_actionPrivileges, privilege._actionPrivileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_idPrivileges,_codePrivileges,_actionPrivileges);
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
}
