package Persistence;

import Entities.M01_Login.Privilege;
import Entities.M10_Profile.Responsability;

import java.util.ArrayList;

public interface IDAOProfile extends IDAO {
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int userId, int companId);
    public Responsability getResponsability(int companyId);
}