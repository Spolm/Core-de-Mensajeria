package Entities.M10_Profile;

public class EditFormHandler {
    private String _addressUser;
    private String _phoneUser;
    private String _emailUser;

    public EditFormHandler() {
    }

    public EditFormHandler(String _addressUser, String _phoneUser, String _emailUser) {
        this._addressUser = _addressUser;
        this._phoneUser = _phoneUser;
        this._emailUser = _emailUser;
    }

    public EditFormHandler(EditRequestBody data) {
        this._addressUser = data.get_addressUser();
        this._phoneUser = data.get_phoneUser();
        this._emailUser = data.get_emailUser();
    }

    public String get_addressUser() {
        return _addressUser;
    }

    public void set_addressUser(String _addressUser) {
        this._addressUser = _addressUser;
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

    public void validate() throws FormErrorException{

        if( ! _emailUser.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") ){
            throw new FormErrorException("Formato de correo no valido");
        }

        if( ! _phoneUser.matches("^[0-9]*$") ){
            throw new FormErrorException("Formato de teléfono no valido");
        }

    }
}
