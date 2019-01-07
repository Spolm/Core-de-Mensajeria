package Persistence.M07_Template;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M02_Company.Company;
import Entities.M05_Channel.Channel;
import Entities.M06_DataOrigin.Application;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.IDAO;

import java.util.ArrayList;

public interface IDAOTemplate extends IDAO {

    public int postTemplate(int campaignId,int applicationId, int userId);

    public boolean postTemplateData(String json);

    public Entity get(int id) throws TemplateDoesntExistsException, MessageDoesntExistsException, ParameterDoesntExistsException;

    public ArrayList<Entity> getAll() throws MessageDoesntExistsException, ParameterDoesntExistsException;

    public Entity getCampaignByTemplate(int id);

    public ArrayList<Template> getTemplatesByCampaign(int userId, int companyId);

    public Application getApplicationByTemplate(int templateId);

    public ArrayList<Channel> getChannelsByTemplate(int templateId);

    public ArrayList<Privilege> getTemplatePrivilegesByUser(int userId, int companyId);

    public boolean updateTemplateData(String json);

    public void updateTemplate(int campaignId,int applicationId, int templateId);
}
