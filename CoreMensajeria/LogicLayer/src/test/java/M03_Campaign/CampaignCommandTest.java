package M03_Campaign;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M03_Campaign.CampaignUserCommand;
import Logic.M03_Campaign.CampaignUserCompanyCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CampaignCommandTest {

    @BeforeEach
    void setUp(){

    }


    @AfterEach
    void tearDown() {
    }

    
    @Test
    void GetCampaignByUserCommandTest(){
        Command<ArrayList<Entity>> command;
        ArrayList<Entity> companies;
        Company _coUser = EntityFactory.CreateCompanyOnlyId( 1);
        command = CommandsFactory.createCampaignUserCommand( _coUser );
        try {
            command.execute();
            companies = ( (CampaignUserCommand) command ).ReturnList();
            assertNotNull(companies);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void CampaignUserCompanyCommandTest(){
        Command<ArrayList<Entity>> command;
        ArrayList<Entity> companies;
        Company _co = EntityFactory.CreateCompanyIDCompUser( 1,1);
        command = CommandsFactory.createCampaignUserCompany( _co );
        try {
            command.execute();
            companies = ( (CampaignUserCompanyCommand) command ).ReturnList();
            assertNotNull(companies);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetCampaignCommandTest(){
        Command command;
        Campaign _ca = EntityFactory.CreateCampaignId( 1 );
        command = CommandsFactory.createGetCampaignCommand( _ca );

        try {
            command.execute();
            _ca = (Campaign) command.Return();
            assertNotNull(_ca);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    




}
