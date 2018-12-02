package M10_Profile;

import Classes.M01_Login.User;
import Classes.M01_Login.UserDAO;
import Classes.M10_Profile.M10_Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class M10_ProfileTest {
    private User _userProfile;
    private M10_Profile _profileDao;
    private UserDAO _userDAO = new UserDAO();
    private User _search;


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

    @BeforeEach
    public void init(){
        newProfile();
        _profileDao= new M10_Profile();
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
    public void loadCorrectProfile() throws SQLException {
        _search = _profileDao.searchUser(_userProfile.get_usernameUser()).get(0);
        assertEquals(_userProfile, _search);
    }

    @Test
    public void loadCorrectProfileError(){
        try {
            _search = _profileDao.searchUser("Alonzo").get(0);
            assertEquals(_userProfile, _search);
        }
        catch(IndexOutOfBoundsException e)
        {
            assertNull(_search);
        }
    }
}
