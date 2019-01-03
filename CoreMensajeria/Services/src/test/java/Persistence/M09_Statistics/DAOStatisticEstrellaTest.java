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
        assertFalse(years.isEmpty());
        assertTrue(years.contains(2018));
        assertTrue(years.contains(2017));
        assertTrue(years.contains(2016));
    }

    @Test
    void getMonths() {
    }

    @Test
    void getDaysofWeek() {
    }

    @Test
    void getDaysofMonth() {
    }

    @Test
    void getDaysofYear() {
    }

    @Test
    void getWeeksofYear() {
    }

    @Test
    void getQuartersofYear() {
    }

    @Test
    void getHours() {
    }

    @Test
    void getMinutes() {
    }

    @Test
    void getSeconds() {
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