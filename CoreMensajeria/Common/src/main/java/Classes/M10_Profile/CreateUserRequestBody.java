package Classes.M10_Profile;

public class CreateUserRequestBody {
    private String _usernameUser;
    private String _emailUser;
    private String _birthdateUser;
    private int _typeUser;
    private String _passwordUser;
    private String _phoneUser;
    private String _countryUser;
    private String _cityUser;
    private String _addressUser;
    private String _genderUser;

    public CreateUserRequestBody() {
    }

    public CreateUserRequestBody(String _usernameUser, String _emailUser, String _birthdateUser, int _typeUser, String _passwordUser, String _phoneUser, String _countryUser, String _cityUser, String _addressUser, String _genderUser) {
        this._usernameUser = _usernameUser;
        this._emailUser = _emailUser;
        this._birthdateUser = _birthdateUser;
        this._typeUser = _typeUser;
        this._passwordUser = _passwordUser;
        this._phoneUser = _phoneUser;
        this._countryUser = _countryUser;
        this._cityUser = _cityUser;
        this._addressUser = _addressUser;
        this._genderUser = _genderUser;
    }

    public String get_usernameUser() {
        return _usernameUser;
    }

    public void set_usernameUser(String _usernameUser) {
        this._usernameUser = _usernameUser;
    }

    public String get_emailUser() {
        return _emailUser;
    }

    public void set_emailUser(String _emailUser) {
        this._emailUser = _emailUser;
    }

    public String get_birthdateUser() {
        return _birthdateUser;
    }

    public void set_birthdateUser(String _burthdateUser) {
        this._birthdateUser = _burthdateUser;
    }

    public int get_typeUser() {
        return _typeUser;
    }

    public void set_typeUser(int _typeUser) {
        this._typeUser = _typeUser;
    }

    public String get_passwordUser() {
        return _passwordUser;
    }

    public void set_passwordUser(String _passwordUser) {
        this._passwordUser = _passwordUser;
    }

    public String get_phoneUser() {
        return _phoneUser;
    }

    public void set_phoneUser(String _phoneUser) {
        this._phoneUser = _phoneUser;
    }

    public String get_countryUser() {
        return _countryUser;
    }

    public void set_countryUser(String _countryUser) {
        this._countryUser = _countryUser;
    }

    public String get_cityUser() {
        return _cityUser;
    }

    public void set_cityUser(String _cityUser) {
        this._cityUser = _cityUser;
    }

    public String get_addressUser() {
        return _addressUser;
    }

    public void set_addressUser(String _addressUser) {
        this._addressUser = _addressUser;
    }

    public String get_genderUser() {
        return _genderUser;
    }

    public void set_genderUser(String _genderUser) {
        this._genderUser = _genderUser;
    }
}
