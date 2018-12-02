package Classes.M01_Login;

public class PasswordChange {

    String _username;
    String _newPassword;
    String _token;

    public PasswordChange() {

    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_newPassword() {
        return _newPassword;
    }

    public void set_newPassword(String _newPassword) {
        this._newPassword = _newPassword;
    }

    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }
}
