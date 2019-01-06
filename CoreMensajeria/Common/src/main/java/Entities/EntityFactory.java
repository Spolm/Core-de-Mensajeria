package Entities;

import Entities.M01_Login.User;
import Entities.M07_Template.MessagePackage.Parameter;

import java.sql.Date;

public class EntityFactory {

    static public Entity user(int id, String _passwordUser, String _usernameUser, int _typeUser, String _emailUser, String _phoneUser, Date _dayOfBirthUser, String _countryUser, String _addressUser, String _genreUser, String _cityUser ) {
        return new User(id, _passwordUser, _usernameUser, _typeUser, _emailUser, _phoneUser, _dayOfBirthUser, _countryUser, _addressUser, _genreUser, _cityUser);
    }

    static public Entity user() {
        return new User();
    }

    public static Parameter CreateParameter(int parameterId, String name, String description){
        return new Parameter(parameterId,name,description);
    }



}
