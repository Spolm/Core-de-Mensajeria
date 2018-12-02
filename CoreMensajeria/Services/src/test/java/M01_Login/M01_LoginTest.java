package M01_Login;

import Classes.M01_Login.LoginIntent;
import org.junit.jupiter.api.Test;
import webService.M01_Login.M01_Login;

import javax.xml.ws.Response;

public class M01_LoginTest {
    private LoginIntent _loginIntent;
    private M01_Login _m01_login;

    /**
     * This method initializes an Intent, which is a representation of a user that wants to log in.
     */
    private void createLoginIntent(){
        _loginIntent = new LoginIntent();
        _loginIntent.set_username("prueba");
        _loginIntent.set_password("Al28*/12");
    }

    @Test
    public void loginTest(){
        Response response = (Response) _m01_login.login(_loginIntent);

    }
}
