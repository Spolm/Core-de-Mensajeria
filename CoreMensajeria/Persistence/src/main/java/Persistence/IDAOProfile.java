package Persistence;

import Entities.M01_Login.Privilege;
import Entities.M10_Profile.Responsability;

import java.util.ArrayList;

public interface IDAOProfile extends IDAO {
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int userId, int companId);

    public ArrayList<Responsability> getResponsabilitiesByCompany(int companyId);

    public String editProfile(int userId, String name, String lastname, int ci, int geographicalRegion, String address,
                              String birthdate, String gender, String email, String phone);
}