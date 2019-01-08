package Entities.M10_Profile;

import Entities.M01_Login.UserDAO;

public class CreateFormHandler {
    
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

    public CreateFormHandler(CreateUserRequestBody body) {
        this._usernameUser = body.get_usernameUser();
        this._emailUser = body.get_emailUser();
        this._birthdateUser = body.get_birthdateUser();
        this._typeUser = body.get_typeUser();
        this._passwordUser = body.get_passwordUser();
        this._phoneUser = body.get_phoneUser();
        this._countryUser = body.get_countryUser();
        this._cityUser = body.get_cityUser();
        this._addressUser = body.get_addressUser();
        this._genderUser = body.get_genderUser();
    }

    public CreateFormHandler() {
    }

    public void validate() throws FormErrorException{

            UserDAO user = new UserDAO();

            if( _emailUser.isEmpty() ){
                throw new FormErrorException("El correo no puede estar vacío");
            }

            if( _usernameUser.isEmpty() ){
                throw new FormErrorException("El nombre de usuario no puede estar vacío");
            }

            if( _phoneUser.isEmpty() ){
                throw new FormErrorException("El teléfono no puede estar vacío");
            }

            if( _passwordUser.isEmpty() ){
                throw new FormErrorException("La contraseña no puede estar vacío");
            }

            if( _genderUser.isEmpty() ){
                throw new FormErrorException("El sexo no puede estar vacío");
            }

            if( _birthdateUser.isEmpty() ){
                throw new FormErrorException("La fecha de nacimiento no puede estar vacía");
            }

            if( ! _emailUser.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") ){
                throw new FormErrorException("Formato de correo no valido");
            }

            if( ! _phoneUser.matches("^[0-9]*$") ){
                throw new FormErrorException("Formato de teléfono no valido");
            }

            if( _passwordUser.length() < 6 ){
                throw new FormErrorException("La contraseña debe tener al menos 6 carácteres");
            }

    }
}
