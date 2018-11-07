package Classes.M01_Login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOTest {
    private LoginIntent _loginIntent;
    private User _user;
    private User _newUser;
    private UserDAO _userDAO = new UserDAO();

    private void createLoginIntent(){
        _loginIntent = new LoginIntent();
        _loginIntent.set_username("prueba");
        _loginIntent.set_password("Al28*/12");
    }

    private  void createUser(){
        _user = new User();
        _user.set_usernameUser("prueba");
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

}
