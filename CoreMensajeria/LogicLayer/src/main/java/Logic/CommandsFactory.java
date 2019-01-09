package Logic;

import Entities.Entity;
import Entities.M07_Template.JSONTemplate;
import Logic.M01_Login.GetUser;
import Logic.M08_SendMessage.CommandScheduleMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.*;
import DTO.M07_Template.NewParameter;
import Logic.M01_Login.GetUser;
import Logic.M08_SendMessage.CommandSendMessage;
import Logic.M08_Validation.*;
import Logic.M07_Template.*;
import Logic.M02_Company.*;
import Logic.M03_Campaign.*;
import Logic.M09_Statistics.*;

import java.util.ArrayList;
import java.util.List;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static Command createScheduleMessage() { return new CommandScheduleMessage(); }

    public static Command createSendMessage() { return new CommandSendMessage(); }

    //region M09
    public static GetAllCompaniesByUserCommand getAllCompaniesByUserCommand(Integer userId) {return new GetAllCompaniesByUserCommand(userId); }
    public static GetCampaignsForCompanyCommand getCampaignsForCompanyCommand(List<Integer> companyIds){return new GetCampaignsForCompanyCommand(companyIds);}
    public static GetAllChannelsCommand getAllChannelsCommand() {return new GetAllChannelsCommand();}
    public static GetIntegratorsForChannelCommand getIntegratorsForChannelCommand(List<Integer> channelIds){ return new GetIntegratorsForChannelCommand(channelIds);}
    public static Command getCompanyStatisticCommand(){ return new GetCompanyStatisticCommand();}
    public static Command getCampaignStatisticCommand(){ return new GetCampaignStatisticCommand();}
    public static Command getChannelStatisticCommand(){ return new GetChannelStatisticCommand();}
    public static Command getIntegratorStatisticCommand(){ return new GetIntegratorStatisticCommand();}
    public static Command updateStarSchema(){ return new UpdateStarSchemaCommand();}
    public static GetYearsCommand getYears(){return new GetYearsCommand();}
    public static GetMonthsCommand getMonths(){return  new GetMonthsCommand();}
    public static GetDaysofWeekCommand getDaysofWeek(){return new GetDaysofWeekCommand();}
    public static GetDaysofMonthCommand getDaysofMonth(){return new GetDaysofMonthCommand();}
    public static GetDaysofYearCommand getDaysofYear(){return new GetDaysofYearCommand();}
    public static GetWeeksofYearCommand getWeeksofYear(){return new GetWeeksofYearCommand();}
    public static GetQuartersofYearCommand getQuartersofYear(){return new GetQuartersofYearCommand();}
    public static GetHoursCommand getHours(){return new GetHoursCommand();}
    public static GetMinutesCommand getMinutes(){return new GetMinutesCommand();}
    public static GetSecondsCommand getSeconds(){return new GetSecondsCommand();}
    public static GetStatisticCommand getStatisticCommand(List<Integer> companyIds, List<Integer> campaignIds, List<Integer> channelIds,
                                                          List<Integer> integratorIds, List<Integer> yearIds, List<Integer> monthIds,
                                                          List<Integer> dayofweekIds, List<Integer> weekofyearIds, List<Integer> dayofmonthIds,
                                                          List<Integer> dayofyearIds, List<Integer> hourofdayIds, List<Integer> minuteofhourIds,
                                                          List<Integer> secondofminuteIds, List<Integer> quarterIds){return new GetStatisticCommand(companyIds, campaignIds, channelIds,
                                                                                                                                                    integratorIds, yearIds, monthIds,
                                                                                                                                                    dayofweekIds, weekofyearIds, dayofmonthIds,
                                                                                                                                                    dayofyearIds, hourofdayIds, minuteofhourIds,
                                                                                                                                                    secondofminuteIds, quarterIds);}

    //endregion


    // region m02
    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }
    public static ChangeStatusCommand createChangeStatusCommand(Entity _co ) {return new ChangeStatusCommand( _co );}
    public static GetAllCompaniesCommand createGetAllCompaniesCommand() {return new GetAllCompaniesCommand();}
    public static GetCompanyCommand createGetCompanyCommand(Entity _co){return new GetCompanyCommand(_co);}
    public static UpdateCompanyCommand createUpdateCompanyCommand(Entity _co) {return new UpdateCompanyCommand(_co);}
    public static GetCompanyByUserCommand createGetCompanyByUserCommand( Entity _co ){
        return new GetCompanyByUserCommand( _co );
    }
    //endregion


    // region m03
    public static UpdateCampaignCommand createUpdateCampaignCommand(Entity _co) {return new UpdateCampaignCommand(_co);}
    public static AddCampaignCommand createAddCampaignCommand(Entity _ca ){ return new AddCampaignCommand( _ca ); }
    public static GetCampaignCommand createGetCampaignCommand(Entity _ca ){ return new GetCampaignCommand( _ca ); }
    public static CampaignUserCommand createCampaignUserCommand(Entity _ca ){ return new CampaignUserCommand( _ca ); }
    /*public static CampaignUserCompanyCommand createCampaignUserCompany( Entity _ca ){
         return new CampaignUserCompanyCommand( _ca  );
      } */
    public static ChangeStatusCampaignCommand createChangeStatusCampaign( Entity _ca ){
        return new ChangeStatusCampaignCommand( _ca );
    }
    //endregion


    //M07_Templates

    public static CommandPostParameter createCommandPostParameter(NewParameter newParameter){
        return new CommandPostParameter(newParameter);
    }

    public static CommandGetParameters createCommandGetParameters(int companyId){
        return new CommandGetParameters(companyId);
    }

    public static CommandGetTemplates createCommandGetTemplates(int userId, int companyId){
        return new CommandGetTemplates(userId, companyId);
    }

    public static CommandGetTemplate createCommandGetTemplate(int templateId){
        return new CommandGetTemplate(templateId);
    }

    public static CommandGetTemplatePrivilegesByUser createCommandGetTemplatePrivilegesByUser(int userId, int companyId){
        return new CommandGetTemplatePrivilegesByUser(userId,companyId);
    }

    public static CommandPostTemplateStatus createCommandPostTemplateStatus(int templateId, int userId){
        return new CommandPostTemplateStatus(templateId,userId);
    }

    public static CommandPostTemplate createCommandPostTemplate(JSONTemplate json){
        return new CommandPostTemplate(json);
    }

    public static CommandUpdateTemplate createCommandUpdateTemplate(String json){
        return new CommandUpdateTemplate(json);
}

    //region M_08
    public static CommandGetTagValue createCommandGetTagValue(String tag, Element element){
        return new CommandGetTagValue(tag,element);
    }

    public static CommandGetParameter createCommandGetParameter(Node node, ArrayList<Parameter> parameter){
        return new CommandGetParameter(node, parameter);
    }

    public static CommandGetMessage createCommandGetMessage(Node node, Template template){
        return new CommandGetMessage(node,template);
    }

    public static CommandProcessXML createCommandProcessXML(String filePath){
        return new CommandProcessXML(filePath);
    }

    public static CommandValidateMessage createCommandValidateMessage(int template, String message,
                                                                      String channel) {
        return new CommandValidateMessage(template, message, channel);

    }

    public static CommandValidateTemplate createCommandValidateTemplate (int id) {
        return new CommandValidateTemplate(id);
    }

    public static CommandValidate createCommandValidate (int template, String message, String channel) {
        return new CommandValidate(template, message, channel);
    }
    //end region
}
