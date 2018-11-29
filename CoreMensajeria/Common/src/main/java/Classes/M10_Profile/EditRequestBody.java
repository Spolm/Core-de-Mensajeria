package Classes.M10_Profile;


public class EditRequestBody {
    private String _addressUser;
    private int _idUser;
    private String _phoneUser;
    private String _emailUser;

    public EditRequestBody() {
    }

    public EditRequestBody(String _addressUser, int _idUser, String _phoneUser, String _emailUser) {
        this._addressUser = _addressUser;
        this._idUser = _idUser;
        this._phoneUser = _phoneUser;
        this._emailUser = _emailUser;
    }

    public String get_addressUser() {
        return _addressUser;
    }

    public void set_addressUser(String _addressUser) {
        this._addressUser = _addressUser;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public String get_phoneUser() {
        return _phoneUser;
    }

    public void set_phoneUser(String _phoneUser) {
        this._phoneUser = _phoneUser;
    }

    public String get_emailUser() {
        return _emailUser;
    }

    public void set_emailUser(String _emailUser) {
        this._emailUser = _emailUser;
    }
}
