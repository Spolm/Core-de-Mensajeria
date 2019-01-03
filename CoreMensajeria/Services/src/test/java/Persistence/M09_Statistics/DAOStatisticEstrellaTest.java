package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M05_Channel.Channel;
import Entities.M05_Channel.ChannelEmail;
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
    }

    @Test
    void getCampaignStatistic() {
    }

    @Test
    void getChannelStatistic() {
    }

    @Test
    void getIntegratorStatistic() {
    }

    @Test
    void updateStarSchema() {
    }

    @Test
    void getYears() {
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