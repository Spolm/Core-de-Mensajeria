package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.IDAO;

import java.util.ArrayList;
import java.util.List;

public interface IDAO_StatisticEstrella extends IDAO {


    ArrayList<Entity> CampaignsForCompany(List<Integer> companyIds) throws CampaignDoesntExistsException;

    ArrayList<Channel> getAllChannels();

    Entity getCompanyStatistic();

    Entity getCampaignStatistic();

    Entity getChannelStatistic();

    Entity getIntegratorStatistic();

    void updateStarSchema();

    ArrayList<Integer> getYears();

    ArrayList<Integer> getMonths();

    ArrayList<Integer> getDaysofWeek();

    ArrayList<Integer> getDaysofMonth();

    ArrayList<Integer> getDaysofYear();

    ArrayList<Integer> getWeeksofYear();

    ArrayList<Integer> getQuartersofYear();

    ArrayList<Integer> getHours();

    ArrayList<Integer> getMinutes();

    ArrayList<Integer> getSeconds();
}