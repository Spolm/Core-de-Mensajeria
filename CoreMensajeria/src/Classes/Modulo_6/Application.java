package Classes.Modulo_6;

//import Classes.User;

import java.util.Date;

public class Application {

    private int _idApplication;
    private String _nameApplication;
    private String _descriptionApplication;
    private String _tokenApplication;
    private Date _dateOfCreateApplication;
    private int _statusApplication;
    private int _userCreatorApplication;

    public Application(int _idApplication, String _nameApplication, String _descriptionApplication, String _tokenApplication, Date _dateOfCreateApplication, int _statusApplication, int _userCreatorApplication) {
        this._idApplication = _idApplication;
        this._nameApplication = _nameApplication;
        this._descriptionApplication = _descriptionApplication;
        this._tokenApplication = _tokenApplication;
        this._dateOfCreateApplication = _dateOfCreateApplication;
        this._statusApplication = _statusApplication;
        this._userCreatorApplication = _userCreatorApplication;
    }

    public Application(){
    }

    public int get_idApplication() {
        return _idApplication;
    }

    public void set_idApplication(int _idApplication) {
        this._idApplication = _idApplication;
    }

    public String get_nameApplication() {
        return _nameApplication;
    }

    public void set_nameApplication(String _nameApplication) {
        this._nameApplication = _nameApplication;
    }

    public String get_descriptionApplication() {
        return _descriptionApplication;
    }

    public void set_descriptionApplication(String _descriptionApplication) {
        this._descriptionApplication = _descriptionApplication;
    }

    public String get_tokenApplication() {
        return _tokenApplication;
    }

    public void set_tokenApplication(String _tokenApplication) {
        this._tokenApplication = _tokenApplication;
    }

    public Date get_dateOfCreateApplication() {
        return _dateOfCreateApplication;
    }

    public void set_dateOfCreateApplication(Date _dateOfCreateApplication) {
        this._dateOfCreateApplication = _dateOfCreateApplication;
    }

    public int get_statusApplication() {
        return _statusApplication;
    }

    public void set_statusApplication(int _statusApplication) {
        this._statusApplication = _statusApplication;
    }

    public int get_userCreatorApplication() {
        return _userCreatorApplication;
    }

    public void set_userCreatorApplication(int _userCreatorApplication) {
        this._userCreatorApplication = _userCreatorApplication;
    }
}
