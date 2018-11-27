package Classes.M06_DataOrigin;

import java.util.Date;

public class Application {

    private int _idApplication;
    private String _nameApplication;
    private String _descriptionApplication;
    private String _tokenApplication;
    private Date _dateOfCreateApplication;
    private int _statusApplication;
    private int _userCreatorId;
    private int _companyId;

    public Application(){
    }

    public Application(int idApplication, String nameApplication, String descriptionApplication,
                       String tokenApplication, Date dateOfCreateApplication, int statusApplication,
                       int userCreatorId, int companyId) {
        _idApplication = idApplication;
        _nameApplication = nameApplication;
        _descriptionApplication = descriptionApplication;
        _tokenApplication = tokenApplication;
        _dateOfCreateApplication = dateOfCreateApplication;
        _statusApplication = statusApplication;
        _userCreatorId = userCreatorId;
        _companyId = companyId;
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

    public int get_userCreatorId() {
        return _userCreatorId;
    }

    public void set_userCreatorId(int userCreatorId) {
        this._userCreatorId = userCreatorId;
    }

    public int get_companyId() {
        return _companyId;
    }

    public void set_companyId(int companyId) {
        this._companyId = companyId;
    }
}
