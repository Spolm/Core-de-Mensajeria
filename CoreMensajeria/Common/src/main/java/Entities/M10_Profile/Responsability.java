package Entities.M10_Profile;

import Entities.Entity;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;

public class Responsability extends Entity {
    private int responsabilityId;
    private User user;
    private Company company;
    private Rol rol;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
