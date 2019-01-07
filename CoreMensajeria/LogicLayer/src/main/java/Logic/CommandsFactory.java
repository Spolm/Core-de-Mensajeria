package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M08_SendMessage.CommandScheduleMessage;
import Logic.M10_Profile.AddUserCommand;
import Logic.M10_Profile.EditUserProfileCommand;
import Logic.M10_Profile.FindUserByIdCommand;
import Logic.M10_Profile.GetAllRolesCommand;
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

    /**
     * Metodo que instancia un objeto del tipo GetAllCompaniesByUserCommand
     * @param userId ID del usuario logeado en el sistema
     * @return un objeto del tipo GetAllCompaniesByUserCommand
     */
    public static Command getAllCompaniesByUserCommand(Integer userId) {return new GetAllCompaniesByUserCommand(userId); }

    /**
     * Metodo que instancia un objeto del tipo GetCampaignsForCompanyCommand
     * @param companyIds Lista de enteros con los ID's de las compañias a las que se quiere obtener sus campañas
     * @return un objeto del tipo GetCampaignsForCompanyCommand
     */

    public static Command getCampaignsForCompanyCommand(List<Integer> companyIds){return new GetCampaignsForCompanyCommand(companyIds);}

    /**
     * Metodo que instancia un objeto del tipo GetAllChannelsCommand
     * @return un objeto del tipo GetAllChannelsCommand
     */

    public static Command getAllChannelsCommand() {return new GetAllChannelsCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetIntegratorsForChannelCommand
     * @param channelIds lista de enteros cons los ID's de los canales a los que se quiere obtener su integrador
     * @return un objeto del tipo GetIntegratorsForChannelCommand
     */

    public static Command getIntegratorsForChannelCommand(List<Integer> channelIds){ return new GetIntegratorsForChannelCommand(channelIds);}

    /**
     * Metodo que instancia un objeto del tipo GetCompanyStatisticCommand
     * @return un objeto del tipo GetCompanyStatisticCommand
     */

    public static Command getCompanyStatisticCommand(){ return new GetCompanyStatisticCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetCampaignStatisticCommand
     * @return un objeto del tipo GetCampaignStatisticCommand
     */

    public static Command getCampaignStatisticCommand(){ return new GetCampaignStatisticCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetChannelStatisticCommand
     * @return un objeto del tipo GetChannelStatisticCommand
     */

    public static Command getChannelStatisticCommand(){ return new GetChannelStatisticCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetIntegratorStatisticCommand
     * @return un objeto del tipo GetIntegratorStatisticCommand
     */

    public static Command getIntegratorStatisticCommand(){ return new GetIntegratorStatisticCommand();}

    /**
     * Metodo que instancia un objeto del tipo UpdateStarSchemaCommand
     * @return un objeto del tipo UpdateStarSchemaCommand
     */

    public static Command updateStarSchemaCommand(){ return new UpdateStarSchemaCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetYearsCommand
     * @return un objeto del tipo GetYearsCommand
     */

    public static Command getYearsCommand(){return new GetYearsCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetMonthsCommand
     * @return un objeto del tipo GetMonthsCommand
     */

    public static Command getMonthsCommand(){return  new GetMonthsCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetDaysofWeekCommand
     * @return un objeto del tipo GetDaysofWeekCommand
     */

    public static Command getDaysofWeekCommand(){return new GetDaysofWeekCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetDaysofMonthCommand
     * @return un objeto del tipo GetDaysofMonthCommand
     */

    public static Command getDaysofMonthCommand(){return new GetDaysofMonthCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetDaysofYearCommand
     * @return un objeto del tipo GetDaysofYearCommand
     */

    public static Command getDaysofYearCommand(){return new GetDaysofYearCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetWeeksofYearCommand
     * @return un objeto del tipo GetWeeksofYearCommand
     */

    public static Command getWeeksofYearCommand(){return new GetWeeksofYearCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetQuartersofYearCommand
     * @return un objeto del tipo GetQuartersofYearCommand
     */

    public static Command getQuartersofYearCommand(){return new GetQuartersofYearCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetHoursCommand
     * @return un objeto del tipo GetHoursCommand
     */

    public static Command getHoursCommand(){return new GetHoursCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetMinutesCommand
     * @return un objeto del tipo GetMinutesCommand
     */

    public static Command getMinutesCommand(){return new GetMinutesCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetSecondsCommand
     * @return un objeto del tipo GetSecondsCommand
     */

    public static Command getSecondsCommand(){return new GetSecondsCommand();}

    /**
     * Metodo que instancia un objeto del tipo GetStatisticCommand
     * @param companyIds Lista con ID's de comañias
     * @param campaignIds Lista con ID's de campañas
     * @param channelIds Lista con Id's de canales
     * @param integratorIds Lista con Id's de integradores
     * @param yearIds Lista con años a buscar
     * @param monthIds  Lista con meses a buscar
     * @param dayofweekIds  Lista con dias de la semana a buscar
     * @param weekofyearIds  Lista con semanas del años buscar
     * @param dayofmonthIds  Lista con dias del mes a buscar
     * @param dayofyearIds  Lista con dias del año a buscar
     * @param hourofdayIds  Lista con horas del dia a buscar
     * @param minuteofhourIds  Lista con minutos de horas a buscar
     * @param secondofminuteIds  Lista con segundos de minutos a buscar
     * @param quarterIds  Lista con cuartos de año a buscar
     * @return un objeto del tipo GetStatisticCommand
     */

    public static Command getStatisticCommand(List<Integer> companyIds, List<Integer> campaignIds, List<Integer> channelIds,
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
    public static CampaignUserCompanyCommand createCampaignUserCompany( Entity _ca ){
         return new CampaignUserCompanyCommand( _ca  );
      }
    public static ChangeStatusCampaignCommand createChangeStatusCampaign( Entity _ca ){
        return new ChangeStatusCampaignCommand( _ca );
    }
    //endregion


    //M07_Templates

    public static CommandGetMessages createCommandGetMessages(){
        return new CommandGetMessages();
    }

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

    public static CommandPostTemplate createCommandPostTemplate(String json){
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

    //region Commands M_10
    public static EditUserProfileCommand createEditUserProfileCommand(){
        return new EditUserProfileCommand();
    }

    public static FindUserByIdCommand createFindUserByIdCommand(){
        return new FindUserByIdCommand();
    }

    public static GetAllRolesCommand createGetAllRolesCommand(){
        return new GetAllRolesCommand();
    }

    public static AddUserCommand createAddUserCommand(){
        return new AddUserCommand();
    }
    //endregion
}
