package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelEmail;
import Entities.M09_Statistics.Statistics;
import Exceptions.CampaignDoesntExistsException;
import Persistence.DAOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOStatisticEstrellaTest {
    DAOStatisticEstrella dao;
    Entity entity;

    @BeforeEach
    void setUp() {
        dao = DAOFactory.instanciateDaoStatisticsEstrella();
    }

    @AfterEach
    void tearDown() {
        dao.bdClose();
        entity = null;
    }

    @Test
    void campaignsForCompany() {
        List<Entity> entitylist;
        List<Integer> companyIds = new ArrayList();
        try {
            //assertThrows(CampaignDoesntExistsException.class, (Executable) dao.CampaignsForCompany(companyIds));
            for (int i = 0; i < 3;i++){
                companyIds.add(i);
            }
            entitylist = dao.CampaignsForCompany(companyIds);
            assertNotNull(entitylist);
        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllChannels() {
        ArrayList<Channel> entitylist;
        entitylist = dao.getAllChannels();
        assertNotNull(entitylist);
        Channel canal = entitylist.get(0);
        assertEquals(canal.getNameChannel(),"SMS");
        canal = entitylist.get(1);
        assertEquals(canal.getNameChannel(),"Email");
    }

    @Test
    void getCompanyStatistic() {
        entity = dao.getCompanyStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getCampaignStatistic() {
        entity = dao.getCampaignStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getChannelStatistic() {
        entity = dao.getChannelStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void getIntegratorStatistic() {
        entity = dao.getIntegratorStatistic();
        assertNotNull(entity);
        assertNotNull(entity.get_id());
        assertNotNull(((Statistics)entity).getX());
        assertNotNull(((Statistics)entity).getY());
    }

    @Test
    void updateStarSchema() {
    }

    @Test
    void getYears() {
        ArrayList<Integer> years;
        years = dao.getYears();
        assertNotNull(years);
        assertTrue(years.contains(2018));
        assertTrue(years.contains(2017));
        assertTrue(years.contains(2016));

    }

    @Test
    void getMonths() {
        ArrayList<Integer> expectedMonths = new ArrayList<>();
        ArrayList<Integer> months = dao.getMonths();
        assertNotNull(months);
        for ( int i=1; i<13; i++ ){
            expectedMonths.add(i);
        }
        assertEquals(expectedMonths, months);
    }

    @Test
    void getDaysofWeek() {
        ArrayList<Integer> expectedDays = new ArrayList<>();
        ArrayList<Integer> days = dao.getDaysofWeek();
        assertNotNull(days);
        for ( int i=1; i<8; i++ ){
            expectedDays.add(i);
        }
        assertEquals(expectedDays, days);
    }

    @Test
    void getDaysofMonth() {
        ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 5, 7, 9, 10, 11, 12, 13, 15, 16, 17, 19, 23,
                24, 25, 28, 29, 30, 31));
        ArrayList<Integer> days = dao.getDaysofMonth();
        assertNotNull(days);
        assertEquals(expectedDays, days);
    }

    @Test
    void getDaysofYear() {
        ArrayList<Integer> expectedDays = new ArrayList<>(Arrays.asList(1, 36, 41, 67, 74, 76, 102, 103, 106, 109, 132,
                175, 181, 204, 240, 243, 253, 254, 285, 296, 314, 330, 333, 359, 365));
        ArrayList<Integer> days = dao.getDaysofYear();
        assertNotNull(days);
        assertEquals(expectedDays, days);
    }

    @Test
    void getWeeksofYear() {
        ArrayList<Integer> expectedWeeks = new ArrayList<>(Arrays.asList(6, 10, 11, 15, 16, 19, 25, 26, 29, 35, 36,
                37, 41, 43, 45, 47, 48, 51, 52, 53));
        ArrayList<Integer> weeks = dao.getWeeksofYear();
        assertNotNull(weeks);
        assertEquals(expectedWeeks, weeks);
    }

    @Test
    void getQuartersofYear() {
        ArrayList<Integer> expectedQuarters = new ArrayList<>();
        ArrayList<Integer> quarters = dao.getQuartersofYear();
        assertNotNull(quarters);
        for ( int i=1; i<5; i++ ){
            expectedQuarters.add(i);
        }
        assertEquals(expectedQuarters, quarters);
    }

    @Test
    void getHours() {
        ArrayList<Integer> expectedHours = new ArrayList<>();
        ArrayList<Integer> hours = dao.getHours();
        assertNotNull(hours);
        expectedHours.add(0);
        assertEquals(expectedHours, hours);
    }

    @Test
    void getMinutes() {
        ArrayList<Integer> expectedMinutes = new ArrayList<>();
        ArrayList<Integer> minutes = dao.getMinutes();
        assertNotNull(minutes);
        expectedMinutes.add(0);
        assertEquals(expectedMinutes, minutes);
    }

    @Test
    void getSeconds() {
        ArrayList<Integer> expectedSeconds = new ArrayList<>();
        ArrayList<Integer> seconds = dao.getSeconds();
        assertNotNull(seconds);
        expectedSeconds.add(0);
        assertEquals(expectedSeconds, seconds);
    }

    @Test
    void getMessagesParamCompany() {
    }

    @Test
    void getMessagesParamCampaign() {
    }

    @Test
    void getMessagesParamChannel() {
    }

    @Test
    void getMessagesParamIntegrator() {
    }
}