package Persistence.M07_Template;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M02_Company.Company;
import Entities.M05_Channel.Channel;
import Entities.M06_DataOrigin.Application;
import Persistence.IDAO;

import java.util.ArrayList;

public interface IDAOTemplate extends IDAO {

    public Entity get(int id);

    public ArrayList<Entity> getAll();

    public Entity getCampaignByTemplate(int id);

    public ArrayList<Company> getTemplatesByCompany(int userID, int companyId);

    public Application getApplicationByTemplate(int templateId);

    public ArrayList<Channel> getChannelsByTemplate(int templateId);

    public ArrayList<Privilege> getTemplatePrivilegesByUser(int userId, int companyId);
}
