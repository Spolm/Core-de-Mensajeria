package Classes.M01_Login;

import java.util.Objects;

public class Privileges {

    private int _idPrivileges;
    private String _codePrivileges;
    private String _actionPrivileges;

    public Privileges() {
    }

    public Privileges(int _idPrivileges, String _codePrivileges, String _actionPrivileges) {
        this._idPrivileges = _idPrivileges;
        this._codePrivileges = _codePrivileges;
        this._actionPrivileges = _actionPrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privileges privileges = (Privileges) o;

        return _idPrivileges == privileges._idPrivileges
                && Objects.equals(_codePrivileges,privileges._codePrivileges)
                && Objects.equals(_actionPrivileges,privileges._actionPrivileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_idPrivileges,_codePrivileges,_actionPrivileges);
    }
}
