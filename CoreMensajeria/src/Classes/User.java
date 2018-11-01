package Classes;

import java.util.Date;

public class User {

    private int _idUser;
    private String _usernameUser;
    private int _typeUser;
    private String _emailUser;
    private String _phoneUser;
    private Date _dayOfBirthUser;
    private String _countryUser;
    private String _addressUser;
    private String _genreUser;
    private String _cityUser;

    public User(int _idUser, String _usernameUser, int _typeUser, String _emailUser, String _phoneUser, Date _dayOfBirthUser, String _countryUser, String _addressUser, String _genreUser, String _cityUser) {
        this._idUser = _idUser;
        this._usernameUser = _usernameUser;
        this._typeUser = _typeUser;
        this._emailUser = _emailUser;
        this._phoneUser = _phoneUser;
        this._dayOfBirthUser = _dayOfBirthUser;
        this._countryUser = _countryUser;
        this._addressUser = _addressUser;
        this._genreUser = _genreUser;
        this._cityUser = _cityUser;
    }

    public User(int idUser, String marco, int typeUser, String emailUser, String phoneUser, int i, String venezuela, String caracas, String masculino, String cityUser) {
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public String get_usernameUser() {
        return _usernameUser;
    }

    public void set_usernameUser(String _usernameUser) {
        this._usernameUser = _usernameUser;
    }

    public int get_typeUser() {
        return _typeUser;
    }

    public void set_typeUser(int _typeUser) {
        this._typeUser = _typeUser;
    }

    public String get_emailUser() {
        return _emailUser;
    }

    public void set_emailUser(String _emailUser) {
        this._emailUser = _emailUser;
    }

    public String get_phoneUser() {
        return _phoneUser;
    }

    public void set_phoneUser(String _phoneUser) {
        this._phoneUser = _phoneUser;
    }

    public Date get_dayOfBirthUser() {
        return _dayOfBirthUser;
    }

    public void set_dayOfBirthUser(Date _dayOfBirthUser) {
        this._dayOfBirthUser = _dayOfBirthUser;
    }

    public String get_countryUser() {
        return _countryUser;
    }

    public void set_countryUser(String _countryUser) {
        this._countryUser = _countryUser;
    }

    public String get_addressUser() {
        return _addressUser;
    }

    public void set_addressUser(String _addressUser) {
        this._addressUser = _addressUser;
    }

    public String get_genreUser() {
        return _genreUser;
    }

    public void set_genreUser(String _genreUser) {
        this._genreUser = _genreUser;
    }

    public String get_cityUser() {
        return _cityUser;
    }

    public void set_cityUser(String _cityUser) {
        this._cityUser = _cityUser;
    }
}
