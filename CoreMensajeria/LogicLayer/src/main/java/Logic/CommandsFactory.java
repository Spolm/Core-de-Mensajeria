package Logic;

import DTO.M08_DTO.ParametersDTO;
import Entities.Entity;
import Entities.M01_Login.User;
import Entities.M06_DataOrigin.AddApplicationData;
import Logic.M04_Integrator.*;
import Logic.M05_Channel.CommandGetAllChannels;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;

//import Logic.M10_Profile.*;
import Entities.M08_Validation.XMLManagement.Message;
import Logic.M01_Login.*;
import Logic.M02_Company.AddCompanyCommand;
import Logic.M08_SendMessage.CommandParseMessage;
import Logic.M08_SendMessage.CommandScheduleMessage;
import Logic.M08_SendMessage.XMLManagment.*;
import Logic.M10_Profile.EditUserProfileCommand;
import Logic.M10_Profile.GetGeographicalRegionCommand;
import Logic.M10_Profile.GetPrivilegesByUserCompanyCommand;
import Logic.M10_Profile.GetResponsabilityByCompanyCommand;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import DTO.M07_Template.NewParameter;
//import Logic.M01_Login.GetUser;
import Logic.M06_DataOrigin.*;
//import Logic.M08_SendMessage.CommandSendMessage;
import Logic.M01_Login.GetUserCommand;
import Logic.M08_Validation.*;
import Logic.M07_Template.*;
import Logic.M02_Company.*;
import Logic.M03_Campaign.*;
import Logic.M09_Statistics.*;

import java.util.ArrayList;
import java.util.List;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUserCommand(user);
    }

    public static GetAllUsersCommand createGetAllUsersCommand () {
        return new GetAllUsersCommand();
    }
    public static LogUserCommand createLogUserCommand (Entity log) {
        return new LogUserCommand(log);
    }
    public static IsBlockedUserCommand isBlockedUserCommand (Entity log) {
        return new IsBlockedUserCommand(log);
    }
    public static TokenGeneratorCommand tokenGeneratorCommand ( String email) {
        return new TokenGeneratorCommand(email);
    }
    public static FindByUsernameOrEmailCommand findByUsernameOrEmailCommand (String username) {
        return new FindByUsernameOrEmailCommand(username);
    }
    public static ChangePasswordCommand changePasswordCommand (String username, String password) {
        return  new ChangePasswordCommand(username, password);
    }
    public static FindPrivilegeByUserIdCommand findPrivilegeByUserIdCommand (int id) {
        return new FindPrivilegeByUserIdCommand(id);
    }
    public static FindByUsernameIdCommand findByUsernameIdCommand (int id) {
        return new FindByUsernameIdCommand(id);
    }

    public static Command createScheduleMessage(VerifiedParameter verifiedParameters) {
        return new CommandScheduleMessage(verifiedParameters);
    }

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

    /**
     * Metodo que instancia un objeto del tipo AddCompanyCommand
     * @return un objeto del tipo AddCompanyCommand
     */
    public static AddCompanyCommand createAddCompanyCommand( Company _co ){

        return new AddCompanyCommand( _co );
    }

    /**
     * Metodo que instancia un objeto del tipo ChangeStatusCommand
     * @return un objeto del tipo ChangeStatusCommand
     */
    public static ChangeStatusCommand createChangeStatusCommand( Company _co  ) {
        return new ChangeStatusCommand( _co );
    }

    /**
     * Metodo que instancia un objeto del tipo GetAllCompaniesCommand
     * @return un objeto del tipo GetAllCompaniesCommand
     */
    public static GetAllCompaniesCommand createGetAllCompaniesCommand() {

        return new GetAllCompaniesCommand();
    }


    /**
     * Metodo que instancia un objeto del tipo GetCompanyCommand
     * @return un objeto del tipo GetCompanyCommand
     */
    public static GetCompanyCommand createGetCompanyCommand(Company _co){

        return new GetCompanyCommand(_co);
    }

    /**
     * Metodo que instancia un objeto del tipo UpdateCompanyCommand
     * @return un objeto del tipo UpdateCompanyCommand
     */
    public static UpdateCompanyCommand createUpdateCompanyCommand(Entity _co) {

        return new UpdateCompanyCommand( _co );
    }

    /**
     * Metodo que instancia un objeto del tipo GetCompanyByUserCommand
     * @return un objeto del tipo GetCompanyByUserCommand
     */
    public static GetCompanyByUserCommand createGetCompanyByUserCommand( Company _co ){
        return new GetCompanyByUserCommand( _co );
    }
    //endregion


    // region m03
    /**
     * Metodo que instancia un objeto del tipo UpdateCampaignCommand
     * @return un objeto del tipo UpdateCampaignCommand
     */
    public static UpdateCampaignCommand createUpdateCampaignCommand(Entity _co) {

        return new UpdateCampaignCommand(_co);
    }

    /**
     * Metodo que instancia un objeto del tipo AddCampaignCommand
     * @return un objeto del tipo AddCampaignCommand
     */
    public static AddCampaignCommand createAddCampaignCommand(Campaign _ca ){

        return new AddCampaignCommand( _ca );
    }

    /**
     * Metodo que instancia un objeto del tipo GetCampaignCommand
     * @return un objeto del tipo GetCampaignCommand
     */
    public static GetCampaignCommand createGetCampaignCommand(Campaign _ca ){

        return new GetCampaignCommand( _ca );
    }

    /**
     * Metodo que instancia un objeto del tipo CampaignUserCommand
     * @return un objeto del tipo CampaignUserCommand
     */
    public static CampaignUserCommand createCampaignUserCommand(Company _ca ){

        return new CampaignUserCommand( _ca );
    }

    /**
     * Metodo que instancia un objeto del tipo CampaignUserCompanyCommand
     * @return un objeto del tipo CampaignUserCompanyCommand
     */
    public static CampaignUserCompanyCommand createCampaignUserCompany( Company _ca ){

        return new CampaignUserCompanyCommand( _ca  );
    }


    /**
     * Metodo que instancia un objeto del tipo ChangeStatusCampaignCommand
     * @return un objeto del tipo ChangeStatusCampaignCommand
     */
    public static ChangeStatusCampaignCommand createChangeStatusCampaign( Campaign _ca ){

        return new ChangeStatusCampaignCommand( _ca );
    }
    //endregion


    //region M06_Origin
    public  static CreateApplicationCommand CreateApplication(AddApplicationData _app){

        return new CreateApplicationCommand(_app);
    }

    public  static GetApplicationByIdCommand GetApplicationId(int _co){

        return new GetApplicationByIdCommand(_co);
    }

    public  static GetApplicationByIdCompanyCommand GetApplicationCompanyId(int _co){

        return new GetApplicationByIdCompanyCommand(_co);
    }

    public  static GetApplicationByTokenCommand GetApplicationToken(String _co){

        return new GetApplicationByTokenCommand(_co);
    }

    public static GetApplicationCommand GetApplication(){

        return  new GetApplicationCommand();
    }

    public static UpdateApplicationCommand UpdateApplication(int _id,int status){

        return new UpdateApplicationCommand(_id,status);
    }
    //endregion


    //M04_Integrator
    public static CommandDisableIntegrator createCommandDisableIntegrator(int id) {
        return new CommandDisableIntegrator(id);
    }

    public static CommandEnableIntegrator createCommandEnableIntegrator(int id) {
        return new CommandEnableIntegrator( id );
    }

    public static CommandGetAllIntegrator createCommandGetAllIntegrators() {
        return new CommandGetAllIntegrator();
    }

    public static CommandGetConcreteIntegrator createCommandGetConcreteIntegrator(int id) {
        return new CommandGetConcreteIntegrator(id);
    }

    public static CommandGetIntegratorByChannel createCommandGetIntegratorByChannel(int id){
        return new CommandGetIntegratorByChannel(id);
    }
    //end M04_Integrator

    //M05_Channels
    public static CommandGetAllChannels createCommandGetAllChannels(){
        return new CommandGetAllChannels();
    }
    //end M05_Channels

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

    public static CommandPostTemplate createCommandPostTemplate(String json){
        return new CommandPostTemplate(json);
    }

    public static CommandUpdateTemplate createCommandUpdateTemplate(String json){
        return new CommandUpdateTemplate(json);
    }

    //region M_08
    public static Command createCommandGetTagValue(String tag, Element element){
        return new CommandGetTagValue(tag,element);
    }

    public static Command createCommandGetParameter(Node node, ArrayList<Parameter> parameter){
        return new CommandGetParameter(node, parameter);
    }

    public static Command createCommandGetMessage(Node node, Template template){
        return new CommandGetMessage(node,template);
    }

    public static Command createCommandProcessXML(String filePath){
        return new CommandProcessXML(filePath);
    }

    public static CommandValidateMessage createCommandValidateMessage(int template, ArrayList<Message> messages) {
        return new CommandValidateMessage(template,messages);

    }

    public static CommandValidateTemplate createCommandValidateTemplate (int id) {
        return new CommandValidateTemplate(id);
    }

    public static CommandValidate createCommandValidate (VerifiedParameter parameters) {
        return new CommandValidate(parameters);
    }

    public static Command createSendMessage(VerifiedParameter parameters) {
        return new CommandSendMessage(parameters);
    }

    public static Command createCommandParseMessage(Message message, Template template){
        return new CommandParseMessage(message, template);
    }

    //end region

    //region Commands M_10
    /**
     * Method that instantiates an object of type EditUserProfileCommand
     * @param userId Id of the user
     * @param companyId Id of the company
     * @return object EditUserProfileCommand
     */
    public static Command createGetPrivilegesByUserCompanyCommand(int userId, int companyId){
        return new GetPrivilegesByUserCompanyCommand(userId, companyId);
    }

    /**
     * Method that instantiates an object of type GetGeographicalRegionCommand
     * @param id id of the GeographicalRegion
     * @return object GetGeographicalRegionCommand
     */
    public static Command createGetGeographicalRegionCommand(int id){
        return new GetGeographicalRegionCommand(id);
    }

    /**
     * Method that instantiates an object of type GetResponsabilityByCompanyCommand
     * @param companyId id of the company
     * @return object GetResponsabilityByCompanyCommand
     */
    public static Command createGetResponsabilityByCompanyCommand(int companyId) {
        return new GetResponsabilityByCompanyCommand(companyId);
    }
    /**
     * Method that instantiates an object of type EditUserProfileCommand
     * @param user to modify
     * @return object EditUserProfileCommand
     */
    public static Command createEditUserProfileCommand(User user){
        return new EditUserProfileCommand(user);
    }
//endregion

}