package Classes.M10_Profile;

import Classes.M01_Login.User;
import Classes.M01_Login.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class M10_ProfileTest {
    private User _userProfile;
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
    public void createProfile(){
        newProfile();
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
    public void loadCorrectProfile(){
            _search = M10_Profile.getInstance().searchUser(_userProfile.get_usernameUser()).get(0);
            assertEquals(_userProfile, _search);
    }

    @Test
    public void loadCorrectProfileError(){
        try {
            _search = M10_Profile.getInstance().searchUser("Alonzo").get(0);
            assertEquals(_userProfile, _search);
        }
        catch(IndexOutOfBoundsException e)
        {
            assertNull(_search);
        }
    }

}
