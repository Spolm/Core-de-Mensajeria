package M10_Profile;

import Entities.M01_Login.User;
import Entities.M01_Login.UserDAO;
import Entities.M10_Profile.M10_Profile;
import Entities.M10_Profile.Rol;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class M10_ProfileTest {
    private User _userProfile;
    private M10_Profile _profileDao;
    private UserDAO _userDAO;
    private ArrayList<Rol> _rol;
    private String _result;
    private User _search;
    private ArrayList<User> _users;
    private ArrayList _companies;
/*
    private void newProfile(){
        _userProfile = new User();
        _userProfile.set_usernameUser("newprofile");
        _userProfile.set_passwordUser("profile");
        _userProfile.set_dateOfBirthUser(Date.valueOf("2018-01-01"));
        _userProfile.set_addressUser("example");
        _userProfile.set_cityUser("example");
        _userProfile.set_genderUser("M");
        _userProfile.set_phoneUser("055");
        _userProfile.set_countryUser("example");
        _userProfile.set_typeUser(1);
        try {
            _userDAO.saveUser(_userProfile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillRoleList(){
        _rol = new ArrayList<>();
        Rol r1 = new Rol(1,"Superusuario");
        Rol r2 = new Rol(2,"Administrador");
        Rol r3 = new Rol(3,"Creador");
        Rol r4 = new Rol(4,"Aprobador");
        Rol r5 = new Rol(5,"Consultor");
        _rol.add(r1);
        _rol.add(r2);
        _rol.add(r3);
        _rol.add(r4);
        _rol.add(r5);
    }

    @BeforeEach
    public void init(){
        _userDAO = new UserDAO();
        _profileDao= new M10_Profile();
        newProfile();
        fillRoleList();

    }

    @AfterEach
    public void deleteProfile(){
        try {
            _userDAO.deleteUser(_userProfile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRoleList(){
        ArrayList<Rol> roltest;
        roltest = _profileDao.getAllRoles();
        assertEquals(_rol.get(0),roltest.get(0));
        assertEquals(_rol.get(1),roltest.get(1));
        assertEquals(_rol.get(2),roltest.get(2));
        assertEquals(_rol.get(3),roltest.get(3));
        assertEquals(_rol.get(4),roltest.get(4));
    }

    @Test
    public void editProfileTest(){
        _result=_profileDao.editProfile(1,"alexdgn213@gmail.com","0414255","Sta Fe");
        assertEquals("Perfil editado con éxito",_result);
    }

    @Test
    public void getUsersTest(){
        _users = _profileDao.getUsers();
        assertNotNull(_users);
    }

    @Test
    public void getCompanies(){
        _companies = _profileDao.getCompanies();
        assertNotNull(_companies);
    }*/
}
