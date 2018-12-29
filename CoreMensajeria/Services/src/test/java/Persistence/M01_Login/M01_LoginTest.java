package Persistence.M01_Login;

import Entities.M01_Login.LoginIntent;
import Entities.M01_Login.User;
import Entities.M01_Login.UserDAO;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webService.M01_Login.M01_Login;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class M01_LoginTest {
    private LoginIntent _loginIntent;
    private M01_Login _m01_login = new M01_Login();
    private User _user;
    private UserDAO _userDAO = new UserDAO();
    Gson _gson = new Gson();

    /**
     * This method initializes an Intent, which is a representation of a user that wants to log in.
     */
    private void createLoginIntent(){
        _loginIntent = new LoginIntent();
        _loginIntent.set_username("prueba");
        _loginIntent.set_password("Al28*/12");
    }

    /**
     * This method initializes an user to make the tests.
     */
    private void createUser(){
        _user = new User();
        _user.set_usernameUser("prueba");
        _user.set_emailUser("prueba@gmail.com");
        _user.set_passwordUser("Al28*/12");
        _user.set_dateOfBirthUser(Date.valueOf("2000-11-01"));
        _user.set_addressUser("kjsaksjd");
        _user.set_cityUser("ksajdl");
        _user.set_genderUser("M");
        _user.set_phoneUser("123123");
        _user.set_countryUser("kjsadk");
        _user.set_typeUser(2);
        try {
            _userDAO.saveUser(_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This calls the initialization methods.
     */
    @BeforeEach
    public void createTestData(){
        createLoginIntent();
        createUser();
    }

    /**
     * This deletes the created data during the tests.
     */
    @AfterEach
    public void deleteTestData(){
        try {
            _userDAO.deleteUser(_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method tests login method.
     */
    @Test
    public void loginTest(){
        Response response = _m01_login.login(_loginIntent);
        assertEquals(_user,_gson.fromJson(response.getEntity().toString(),User.class));
    }

    /**
     * This method tests login method with wrong password.
     */
    @Test
    public void loginFailTest(){
        _loginIntent.set_password("CLave1");
        Response response = _m01_login.login(_loginIntent);
        assertEquals(404,response.getStatus());
    }


}
