package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Exceptions.CampaignDoesntExistsException;
import Exceptions.CompanyDoesntExistsException;
import Persistence.IDAO;

import java.util.ArrayList;
import java.util.List;

public interface IDAO_Statistic extends IDAO {

    ArrayList<Entity> getAllCompanies(Integer userId) throws CompanyDoesntExistsException;

    ArrayList<Campaign> CampaignsForCompany(List<Integer> companyIds) throws CampaignDoesntExistsException;

}
