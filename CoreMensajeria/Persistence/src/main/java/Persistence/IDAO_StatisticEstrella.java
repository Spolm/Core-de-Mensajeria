package Persistence;

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

    ArrayList<Entity> getAllChannels();

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

    Entity getMessagesParamCompany(String companyIds, String campaignIds, String channelIds, String integratorIds, String yearIds,
                                          String monthIds, String dayofweekIds, String weekofyearIds, String dayofmonthIds, String dayofyearIds,
                                          String hourIds, String minuteIds, String secondIds, String quarterIds );

    Entity getMessagesParamCampaign(String companyIds, String campaignIds, String channelIds, String integratorIds, String yearIds,
                                    String monthIds, String dayofweekIds, String weekofyearIds, String dayofmonthIds, String dayofyearIds,
                                    String hourIds, String minuteIds, String secondIds, String quarterIds );

    Entity getMessagesParamChannel(String companyIds, String campaignIds, String channelIds, String integratorIds, String yearIds,
                                   String monthIds, String dayofweekIds, String weekofyearIds, String dayofmonthIds, String dayofyearIds,
                                   String hourIds, String minuteIds, String secondIds, String quarterIds );

    Entity getMessagesParamIntegrator(String companyIds, String campaignIds, String channelIds, String integratorIds, String yearIds,
                                      String monthIds, String dayofweekIds, String weekofyearIds, String dayofmonthIds, String dayofyearIds,
                                      String hourIds, String minuteIds, String secondIds, String quarterIds );

    void close();
}
