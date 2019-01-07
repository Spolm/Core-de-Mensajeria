package DTO.M01_DTO;

import java.sql.Date;
import DTO.DTO;

public class DTOUser extends DTO {

    private int _idUser;
    private String _password;
    private String _username;
    private int _type;
    private String _email;
    private String _phone;
    private Date _dateOfBirth;
    private String _country;
    private String _address;
    private String _gender;
    private String _city;
    private Integer _blocked;
    private Integer _remainingAttempts;

    public DTOUser(int _idUser, String _password, String _username, int _type, String _email, String _phone, Date _dateOfBirth, String _country, String _address, String _gender, String _city, Integer _blocked, Integer _remainingAttempts) {
        this._idUser = _idUser;
        this._password = _password;
        this._username = _username;
        this._type = _type;
        this._email = _email;
        this._phone = _phone;
        this._dateOfBirth = _dateOfBirth;
        this._country = _country;
        this._address = _address;
        this._gender = _gender;
        this._city = _city;
        this._blocked = _blocked;
        this._remainingAttempts = _remainingAttempts;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public int get_type() {
        return _type;
    }

    public void set_type(int _type) {
        this._type = _type;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public Date get_dateOfBirth() {
        return _dateOfBirth;
    }

    public void set_dateOfBirth(Date _dateOfBirth) {
        this._dateOfBirth = _dateOfBirth;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public Integer get_blocked() {
        return _blocked;
    }

    public void set_blocked(Integer _blocked) {
        this._blocked = _blocked;
    }

    public Integer get_remainingAttempts() {
        return _remainingAttempts;
    }

    public void set_remainingAttempts(Integer _remainingAttempts) {
        this._remainingAttempts = _remainingAttempts;
    }

    public DTOUser() {
    }
}
