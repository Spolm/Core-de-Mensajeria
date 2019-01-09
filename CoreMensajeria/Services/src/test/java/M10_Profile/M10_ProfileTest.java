package M10_Profile;

import DTO.M10_DTO.DTOEditUser;
import org.junit.jupiter.api.Test;
import webService.M03_CampaignManagement.M03_Campaigns;
import webService.M10_Profile.M10_Profile;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M10_ProfileTest {
    @Test
    void editUser() {

        try {
            DTOEditUser dto = new DTOEditUser(5, "Ruben", "Luna", 25217437, 359, "El Llanito",
                    "1996-08-16",  "M", "ruben@gmail.com", "04242237909");
            M10_Profile instance = new M10_Profile();
            Response _salida =  instance.editProfile(dto);
            assertEquals(200, _salida.getStatus() );
            assertNotNull( _salida.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
