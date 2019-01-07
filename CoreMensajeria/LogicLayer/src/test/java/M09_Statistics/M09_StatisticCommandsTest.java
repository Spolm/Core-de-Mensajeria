package M09_Statistics;

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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class M09_StatisticCommandsTest {

    private Entity entity;

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

    @Test
    void GetDaysofMonthCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> days;
        command = CommandsFactory.getDaysofMonthCommand();
        ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 5, 7, 9, 10, 11, 12, 13, 15, 16, 17,
                19, 23, 24, 25, 28, 29, 30, 31));
        try {
            command.execute();
            days = command.Return();
            assertNotNull(days);
            assertEquals(expectedDays, days);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetDaysofWeekCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> days;
        command = CommandsFactory.getDaysofWeekCommand();
        ArrayList<Integer> expectedDays = new ArrayList<>();
        for ( int i = 1; i < 8; i++){
            expectedDays.add(i);
        }
        try {
            command.execute();
            days = command.Return();
            assertNotNull(days);
            assertEquals(expectedDays, days);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetDaysofYearCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> days;
        command = CommandsFactory.getDaysofYearCommand();
        ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 36, 41, 67, 74, 76, 102, 103, 106, 109, 132,
                175, 181, 204, 240, 243, 253, 254, 285, 296, 314, 330, 333, 359, 365));
        try {
            command.execute();
            days = command.Return();
            assertNotNull(days);
            assertEquals(expectedDays, days);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetHoursCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> hours;
        command = CommandsFactory.getHoursCommand();
        ArrayList<Integer> expectedHours = new ArrayList<>();
        expectedHours.add(0);
        try {
            command.execute();
            hours = command.Return();
            assertNotNull(hours);
            assertEquals(expectedHours, hours);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetMinutesCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> minutes;
        command = CommandsFactory.getMinutesCommand();
        ArrayList<Integer> expectedMinutes = new ArrayList<>();
        expectedMinutes.add(0);
        try {
            command.execute();
            minutes = command.Return();
            assertNotNull(minutes);
            assertEquals(expectedMinutes, minutes);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetMonthsCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> months;
        command = CommandsFactory.getMonthsCommand();
        ArrayList<Integer> expectedMonths = new ArrayList<>();
        for ( int i = 1; i < 13; i++){
            expectedMonths.add(i);
        }
        try {
            command.execute();
            months = command.Return();
            assertNotNull(months);
            assertEquals(expectedMonths, months);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetQuartersofYearCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> quarters;
        command = CommandsFactory.getQuartersofYearCommand();
        ArrayList<Integer> expectedQuarters = new ArrayList<>();
        for ( int i = 1; i < 5; i++){
            expectedQuarters.add(i);
        }
        try {
            command.execute();
            quarters = command.Return();
            assertNotNull(quarters);
            assertEquals(expectedQuarters, quarters);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetSecondsCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> seconds;
        command = CommandsFactory.getSecondsCommand();
        ArrayList<Integer> expectedSeconds = new ArrayList<>();
        expectedSeconds.add(0);
        try {
            command.execute();
            seconds = command.Return();
            assertNotNull(seconds);
            assertEquals(expectedSeconds, seconds);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetWeeksofYearCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> weeks;
        command = CommandsFactory.getWeeksofYearCommand();
        ArrayList<Integer> expectedWeeks = new ArrayList<>(Arrays.asList(6, 10, 11, 15, 16, 19, 25, 26, 29, 35, 36,
                37, 41, 43, 45, 47, 48, 51, 52, 53));
        try {
            command.execute();
            weeks = command.Return();
            assertNotNull(weeks);
            assertEquals(expectedWeeks, weeks);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetYearsCommand(){
        Command<ArrayList> command;
        ArrayList<Integer> years;
        command = CommandsFactory.getYearsCommand();
        ArrayList<Integer> expectedYears = new ArrayList<>();
        for ( int i = 2016; i < 2019; i++){
            expectedYears.add(i);
        }
        try {
            command.execute();
            years = command.Return();
            assertNotNull(years);
            assertEquals(expectedYears, years);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void GetStatisticsCommand(){
        Command<Map<String, Entity>> command;
        Map<String, Entity> stats;
        List<Integer> companiesId = new ArrayList<>();
        companiesId.add(1);
        List<Integer> campaignIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> channelIds = new ArrayList<>(Arrays.asList(1 ,2));
        List<Integer> integratorIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> yearIds = new ArrayList<>();
        List<Integer> monthIds = new ArrayList<>();
        List<Integer> dayofweekIds = new ArrayList<>();
        List<Integer> weekofyearIds = new ArrayList<>();
        List<Integer> dayofmonthIds = new ArrayList<>();
        List<Integer> dayofyearIds = new ArrayList<>();
        List<Integer> hourofdayIds = new ArrayList<>();
        List<Integer> minuteofhourIds = new ArrayList<>();
        List<Integer> secondofminuteIds = new ArrayList<>();
        List<Integer> quarterIds = new ArrayList<>();
        ArrayList<String> expectedIntegrators = new ArrayList<>(Arrays.asList("Movistar", "Digitel", "Movilnet",
                "MailChimp", "Aweber", "Infusionsoft"));
        ArrayList<Integer> expectedIntegratorsMessages = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3, 3));
        ArrayList<String> expectedCompanies = new ArrayList<>();
        expectedCompanies.add("Company 1");
        ArrayList<Integer> expectedCompaniesMessages = new ArrayList<>();
        expectedCompaniesMessages.add(18);
        ArrayList<String> expectedCampaigns = new ArrayList<>(Arrays.asList("Campaign 1", "Campaign 2", "Campaign 3",
                "Campaign 4", "Campaign 5"));
        ArrayList<Integer> expectedCampaignsMessages = new ArrayList<>(Arrays.asList(6, 3, 4, 2, 3));
        ArrayList<String> expectedChannels = new ArrayList<>(Arrays.asList("SMS", "Email"));
        ArrayList<Integer> expectedChannelsMessages = new ArrayList<>(Arrays.asList(9, 9));
        command = CommandsFactory.getStatisticCommand(companiesId, campaignIds, channelIds, integratorIds, yearIds,
                monthIds, dayofweekIds, weekofyearIds, dayofmonthIds, dayofyearIds, hourofdayIds, minuteofhourIds,
                secondofminuteIds, quarterIds);
        try {
            command.execute();
            stats = command.Return();
            assertNotNull(stats);
            assertEquals(4, stats.size());
            assertTrue(expectedIntegrators.containsAll(((Statistics)stats.get("integrators")).getX()));
            assertTrue(expectedIntegratorsMessages.containsAll(((Statistics)stats.get("integrators")).getY()));
            assertTrue(expectedCompanies.containsAll(((Statistics)stats.get("companies")).getX()));
            assertTrue(expectedCompaniesMessages.containsAll(((Statistics)stats.get("companies")).getY()));
            assertTrue(expectedCampaigns.containsAll(((Statistics)stats.get("campaigns")).getX()));
            assertTrue(expectedCampaignsMessages.containsAll(((Statistics)stats.get("campaigns")).getY()));
            assertTrue(expectedChannels.containsAll(((Statistics)stats.get("channels")).getX()));
            assertTrue(expectedChannelsMessages.containsAll(((Statistics)stats.get("channels")).getY()));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}