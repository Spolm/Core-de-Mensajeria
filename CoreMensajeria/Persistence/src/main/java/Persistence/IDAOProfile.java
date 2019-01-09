package Persistence;

import Entities.M01_Login.Privilege;
import Entities.M10_Profile.Responsability;

import java.util.ArrayList;

/**
 * Interface responsible for the activities in the particular database of user profile
 */
public interface IDAOProfile extends IDAO {
    /**
     * Interface to obtain the privileges by user and company
     * @param userId id of the user
     * @param companId id of the company
     * @return list of privileges by user and company
     */
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int userId, int companId);

    public Responsability getResponsability(int companyId);

    public String editProfile(int userId, String name, String lastname, int ci, int geographicalRegion, String address,
                              String birthdate, String gender, String email, String phone);
}