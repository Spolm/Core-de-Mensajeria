package Persistence;

import Entities.M01_Login.Privilege;

import java.util.ArrayList;

public interface IDAOProfile extends IDAO {
    public ArrayList<Privilege> getPrivilegesByUserAndCompany(int user, int company);
}