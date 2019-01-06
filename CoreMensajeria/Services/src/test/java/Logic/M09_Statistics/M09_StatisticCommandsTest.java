package Logic.M09_Statistics;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M05_Channel.Channel;
import Entities.M09_Statistics.Statistics;
import Logic.Command;
import Logic.CommandsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class M09_StatisticCommandsTest {

    private Entity entity;
    private Command command;

    @BeforeEach
    void setUp(){}

    @AfterEach
    void tearDown() {
    }

    @Test
    void GetAllChannelsCommand(){
        Command<ArrayList<Channel>> command;
        command = CommandsFactory.getAllChannelsCommand();
        ArrayList<Channel> channels;
        try {
            command.execute();
            channels = command.Return();
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
        Command<ArrayList<Entity>> command;
        ArrayList<Entity> companies;
        command = CommandsFactory.getAllCompaniesByUserCommand(3);
        try {
            command.execute();
            companies = command.Return();
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
        Command<ArrayList<Entity>> command;
        ArrayList<Entity> companies;
        command = CommandsFactory.getAllCompaniesByUserCommand(0);
        try {
            command.execute();
            companies = command.Return();
            assertNotNull(companies);
            assertEquals(0,companies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetCompanyStatisticCommand(){
        Command<Entity> command;
        command = CommandsFactory.getCompanyStatisticCommand();
        ArrayList<String> expectedCompanies = new ArrayList<>(Arrays.asList("Company 1", "Company 2", "Company 3",
                "Company 4"));
        ArrayList<Integer> expectedMessagesCount = new ArrayList<>(Arrays.asList(18, 7, 2, 2));
        try {
            command.execute();
            entity = command.Return();
            assertNotNull(entity);
            assertEquals(4, ((Statistics)entity).getX().size());
            assertEquals(4, ((Statistics)entity).getY().size());
            assertTrue(expectedCompanies.containsAll(((Statistics) entity).getX()));
            assertTrue(expectedMessagesCount.containsAll(((Statistics) entity).getY()));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetCampaignsForCompanyCommand(){
        Command<ArrayList<Entity>> command;
        ArrayList<Integer> campaignsIds = new ArrayList<>(Arrays.asList(1,2,3));
        ArrayList<Entity> campaigns;
        command = CommandsFactory.getCampaignsForCompanyCommand(campaignsIds);
        try {
            command.execute();
            campaigns = command.Return();
            assertNotNull(campaigns);
            assertEquals(12, campaigns.size());
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetCampaignStatisticCommand(){
        Command<Entity> command;
        command = CommandsFactory.getCampaignStatisticCommand();
        ArrayList<String> expectedCampaigns = new ArrayList<>(Arrays.asList("Campaign 1", "Campaign 2", "Campaign 3",
                "Campaign 4", "Campaign 5", "Campaign 6", "Campaign 7", "Campaign 8", "Campaign 9", "Campaign 10",
                "Campaign 11", "Campaign 12", "Campaign 13", "Campaign 14"));
        ArrayList<Integer> expectedMessagesCount = new ArrayList<>(Arrays.asList(6, 3, 4, 2, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1));
        try {
            command.execute();
            entity = command.Return();
            assertNotNull(entity);
            assertEquals(14, ((Statistics)entity).getX().size());
            assertEquals(14, ((Statistics)entity).getY().size());
            assertTrue(expectedCampaigns.containsAll(((Statistics) entity).getX()));
            assertTrue(expectedMessagesCount.containsAll(((Statistics) entity).getY()));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetChannelStatisticCommand(){
        Command<Entity> command;
        command = CommandsFactory.getChannelStatisticCommand();
        ArrayList<String> expectedChannels = new ArrayList<>(Arrays.asList("SMS", "Email"));
        ArrayList<Integer> expectedMessagesCount = new ArrayList<>(Arrays.asList(15, 14));
        try {
            command.execute();
            entity = command.Return();
            assertNotNull(entity);
            assertEquals(2, ((Statistics)entity).getX().size());
            assertEquals(2, ((Statistics)entity).getY().size());
            assertTrue(expectedChannels.containsAll(((Statistics) entity).getX()));
            assertTrue(expectedMessagesCount.containsAll(((Statistics) entity).getY()));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetIntegratorStatisticCommand(){
        Command<Entity> command;
        command = CommandsFactory.getIntegratorStatisticCommand();
        ArrayList<String> expectedIntegrators = new ArrayList<>(Arrays.asList("Movistar", "Digitel", "Movilnet",
                "MailChimp", "Aweber", "Infusionsoft"));
        ArrayList<Integer> expectedMessagesCount = new ArrayList<>(Arrays.asList(5, 5, 5, 5, 5, 4));
        try {
            command.execute();
            entity = command.Return();
            assertNotNull(entity);
            assertEquals(6, ((Statistics)entity).getX().size());
            assertEquals(6, ((Statistics)entity).getY().size());
            assertTrue(expectedIntegrators.containsAll(((Statistics) entity).getX()));
            assertTrue(expectedMessagesCount.containsAll(((Statistics) entity).getY()));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}