package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.IDAO;

import java.util.ArrayList;
import java.util.List;

public interface IDAO_Statistic extends IDAO {

    ArrayList<Entity> getAllCompanies(Integer userId) throws CompanyDoesntExistsException;

    ArrayList<Entity> CampaignsForCompany(List<Integer> companyIds) throws CampaignDoesntExistsException;

    ArrayList<Channel> getAllChannels();
}
