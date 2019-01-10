package M10_Profile;

import DTO.M10_DTO.DTOEditUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webService.M10_Profile.M10_GeographicalRegion;
import webService.M10_Profile.M10_Profile;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M10_ProfileTest {
    M10_Profile instance = null;
    M10_GeographicalRegion instance2 = null;

    @BeforeEach
    void setUp() {
        instance = new M10_Profile();
        instance2 = new M10_GeographicalRegion();
    }

    @Test
    public void editUser() {
        try {
            DTOEditUser dto = new DTOEditUser(5, "Ruben", "Luna", 25217437, 359, "El Llanito",
                    "1996-08-16",  "M", "ruben@gmail.com", "04242237909");
            Response exit =  instance.editProfile(dto);
            assertEquals(200, exit.getStatus() );
            assertNotNull( exit.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void getPrivileges() {
        try {
            Response exit =  instance.getPrivileges(3,1);
            assertEquals(200, exit.getStatus() );
            assertNotNull( exit.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void getResponsability() {
        try {
            Response exit =  instance.getResponsability(1);
            assertEquals(200, exit.getStatus() );
            assertNotNull( exit.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }
    @Test
    public void getRegionByType(){
        try {
            Response exit =  instance2.getRegionByType(1);
            assertEquals(200, exit.getStatus() );
            assertNotNull( exit.getEntity());
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
