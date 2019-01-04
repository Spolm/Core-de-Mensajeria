package Logic.M09_Statistics;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M05_Channel.Channel;
import Exceptions.CompanyDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class M09_StatisticCommandsTest {

    Entity entity;
    Command command;

    @BeforeEach
    void setUp(){}

    @AfterEach
    void tearDown() {
        command = null;
    }

    @Test
    void GetAllChannelsCommand(){
        command = CommandsFactory.getAllChannelsCommand();
        ArrayList<Channel> channels;
        try {
            command.execute();
            channels = ((GetAllChannelsCommand) command).returnList();
            assertNotNull(channels);
            Channel canal = channels.get(0);
            assertEquals(canal.getNameChannel(),"SMS");
            canal = channels.get(1);
            assertEquals(canal.getNameChannel(),"Email");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetAllCompaniesByUserCommand(){
        ArrayList<Entity> companies;
        command = CommandsFactory.getAllCompaniesByUserCommand(3);
        try {
            command.execute();
            companies = ((GetAllCompaniesByUserCommand) command).returnList();
            assertNotNull(companies);
            assertEquals(3, companies.size());
            assertTrue(((Company)companies.get(0)).get_name().contains("Company 1"));
            assertTrue(((Company)companies.get(1)).get_name().contains("Company 2"));
            assertTrue(((Company)companies.get(2)).get_name().contains("Company 3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetAllCompaniesByUserCommandDontReturn(){
        ArrayList<Entity> companies;
        command = CommandsFactory.getAllCompaniesByUserCommand(0);
        try {
            command.execute();
            companies = ((GetAllCompaniesByUserCommand) command).returnList();
            assertNotNull(companies);
            assertEquals(0,companies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetCampaignsForCompanyCommand(){}
}