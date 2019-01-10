package DTO.M10_DTO;

import DTO.DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DTOEditUser extends DTO {
    int id;
    String name;
    String lastname;
    int ci;
    int gr;
    String address;
    String birthdate;
    String gender;
    String email;
    String phone;

    public DTOEditUser() {
    }

    public DTOEditUser(int id, String name, String lastname, int ci, int gr, String address, String birthdate, String gender, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.ci = ci;
        this.gr = gr;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getGr() {
        return gr;
    }

    public void setGr(int gr) {
        this.gr = gr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
