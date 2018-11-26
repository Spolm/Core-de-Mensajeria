package Classes.M06_DataOrigin;

public class AddApplicationData {

    private String _nameApplication;
    private String _descriptionApplication;
    private int _userId;
    private int _companyId;

    public AddApplicationData (){
    }

    public AddApplicationData(String nameApplication, String descriptionApplication, int userId, int companyId){
        _nameApplication = nameApplication;
        _descriptionApplication = descriptionApplication;
        _userId = userId;
        _companyId = companyId;
    }

    public String get_nameApplication() {
        return _nameApplication;
    }

    public void set_nameApplication(String nameApplication) {
        this._nameApplication = nameApplication;
    }

    public String get_descriptionApplication() {
        return _descriptionApplication;
    }

    public void set_descriptionApplication(String descriptionApplication) {
        this._descriptionApplication = descriptionApplication;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public int get_companyId() {
        return _companyId;
    }

    public void set_companyId(int _companyId) {
        this._companyId = _companyId;
    }
}
