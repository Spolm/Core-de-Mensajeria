package Persistence;

import Entities.M01_Login.Privilege;
import Entities.M02_Company.Company;
import Entities.M10_Profile.Responsability;
import Entities.M10_Profile.Rol;

import java.util.ArrayList;

/**
 * Interface responsible for the activities in the particular database of user profile
 */
public interface IDAOProfile extends IDAO {
    /**
     * Abstract method to obtain the privileges by user and company
     * @param userId id of the user
     * @param companId id of the company
     * @return ArrayList<Privilege> list of privileges by user and company
     */
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int userId, int companId);


    /**
     * Abstract method to return the responsibilities  by company
     * @param companyId id of the company
     * @return  ArrayList<Responsability> responsibilities by company
     */
    public ArrayList<Responsability> getResponsabilitiesByCompany(int companyId);


    /**
     * Abstract method which is responsible for editing a user profile
     * @param userId id of the user
     * @param name  new name of the user
     * @param lastname new lastname of the user
     * @param ci new identification number of the user
     * @param geographicalRegion new geographical region of the user
     * @param address new address of the user
     * @param birthdate new birthdate of the user
     * @param gender new gender of the user
     * @param email new email of the user
     * @param phone new phone of the user
     * @return String with information about the data base
     */
    public String editProfile(int userId, String name, String lastname, int ci, int geographicalRegion, String address,
                              String birthdate, String gender, String email, String phone);

    public ArrayList<Company> getCompaniesByUser(int userId);

    public ArrayList<Rol> getRoles();
}