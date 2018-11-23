package Classes.M06_DataOrigin;


public class AddApplicationData {

    private String _nameApplication;
    private String _descriptionApplication;
    private int _userId;

    public AddApplicationData (){

    }

    public AddApplicationData(String _nameApplication,String _descriptionApplication, int _userId){
        this._nameApplication = _nameApplication;
        this._descriptionApplication = _descriptionApplication;
        this._userId = _userId;
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

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }
}
