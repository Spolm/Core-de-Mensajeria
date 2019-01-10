package M10_Profile;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.Privilege;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;
import Entities.M10_Profile.GeographicalRegion;
import Entities.M10_Profile.Responsability;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class M10_ProfileCommandsTest {

    @Test
    public void getPrivilegesByUserCompanyCommand(){
        ArrayList<Privilege> privileges;
        try {
            Command c = CommandsFactory.createGetPrivilegesByUserCompanyCommand(3, 1);
            c.execute();
            privileges =  (ArrayList<Privilege>)c.Return();
            assertEquals(1, privileges.get(0).get_idPrivileges());
            assertEquals(2, privileges.get(1).get_idPrivileges());
            assertEquals(3, privileges.get(2).get_idPrivileges());
            assertEquals(4, privileges.get(3).get_idPrivileges());
        } catch (Exception  e){
            e.printStackTrace();
        }
    }

    @Test
    public void GetResponsabilityByCompanyCommand(){
        ArrayList<Responsability> responsabilities;
        try {
            Command c = CommandsFactory.createGetResponsabilityByCompanyCommand(1);
            c.execute();
            responsabilities =  (ArrayList<Responsability>)c.Return();
            assertEquals(4, responsabilities.size());

        } catch (Exception  e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCompaniesByUser(){
        ArrayList<Company> companies;
        try {
            Command c = CommandsFactory.createGetCompaniesByUserCommand(3);
            c.execute();
            companies =  (ArrayList<Company>)c.Return();

            assertEquals(3, companies.size());
        } catch (Exception  e){
            e.printStackTrace();
        }
    }

    @Test
    public void editProfile(){
        try {
            Entity e = EntityFactory.CreateUser(4, null, null, 0, "prueba@gmail.com",
                    "04242237909", null, null, "Petare", "M", null,
                    "Crea", "Dor", 25217437, 359, "1996-08-16");
            User user = (User) e;
            Command c = CommandsFactory.createEditUserProfileCommand(user);
            c.execute();
            String r = (String) c.Return();
            assertEquals("Perfil editado con éxito", r);
        } catch (Exception  e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRegionByType(){
        ArrayList<GeographicalRegion> geographicalRegions;
        try {
            Command c = CommandsFactory.createGetGeographicalRegionCommand(1);
            c.execute();
            geographicalRegions = (ArrayList<GeographicalRegion>) c.Return();
            for (int i = 0; i < geographicalRegions.size(); i++)
                assertEquals(i+2,geographicalRegions.get(i).getGeographicalRegionId());

        } catch (Exception  e){
            e.printStackTrace();
        }
    }
}
