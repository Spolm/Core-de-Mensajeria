package M01_Login;

import Classes.M01_Login.LoginIntent;
import Classes.M01_Login.User;
import Classes.M01_Login.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest {
    private LoginIntent _loginIntent;
    private User _user;
    private User _newUser;
    private ArrayList<User> _userList;
    private UserDAO _userDAO = new UserDAO();

    private void createLoginIntent(){
        _loginIntent = new LoginIntent();
        _loginIntent.set_username("prueba");
        _loginIntent.set_password("Al28*/12");
    }

    private  void createUser(){
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

    @BeforeEach
    public void createTestData(){
        createLoginIntent();
        createUser();
    }

    @AfterEach
    public void deleteTestData(){
        try {
            _userDAO.deleteUser(_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByUsernameOrEmailTest(){
        try {
            _newUser = _userDAO.findByUsernameOrEmail(_user.get_usernameUser());
            assertEquals(_user,_newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByUsernameIdTest(){
        try{
            _newUser = _userDAO.findByUsernameId(_user.get_idUser());
            assertEquals(_user,_newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest(){
        try{
            ArrayList list = _userDAO.findAll();
            assertTrue(list.size()>=1);
        }
        catch (SQLException e){

        }
    }

    @Test
    public void saveUserTest(){
        try {
            User user = _userDAO.findByUsernameId(_user.get_idUser());
            assertEquals(user,_user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserTest(){
            try {
                _userDAO.deleteUser(_user);
               assertNull(_userDAO.findByUsernameId(_user.get_idUser()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
