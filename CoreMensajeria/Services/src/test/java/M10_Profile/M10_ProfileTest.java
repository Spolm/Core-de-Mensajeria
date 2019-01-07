package M10_Profile;

import org.junit.Test;
import webService.M10_Profile.M10_Profile;
import javax.ws.rs.core.Response;

import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M10_ProfileTest {

    @Test
    public void getPrivilegesByUser(){
        try{
            M10_Profile instance = new M10_Profile();
            Response salida = instance.getPrivileges(3,1);
            assertEquals(200,salida.getStatus());
            assertNotNull(salida.getEntity());
            System.out.println(salida);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
