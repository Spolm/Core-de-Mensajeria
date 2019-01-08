package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M04_Integrator.*;
import Logic.M05_Channel.CommandGetAllChannels;
import Logic.M07_Template.*;
import Logic.M02_Company.*;
import Logic.M03_Campaign.*;
import Logic.M09_Statistics.*;

import java.util.List;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }


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


    // region M04_Integrator
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

    public static CommandGetAllChannels instanceGetAllChannels(){ return new CommandGetAllChannels(); }
    //endregion

    //M07_Templates

    public static CommandGetMessages createCommandGetMessages(){
        return new CommandGetMessages();
    }


    public static CommandPostParameter createCommandPostParameter(){
        return new CommandPostParameter();
    }

    public static CommandGetParameters createCommandGetParameters(){
        return new CommandGetParameters();
    }

    public static CommandGetTemplates createCommandGetTemplates(){
        return new CommandGetTemplates();
    }

    public static CommandGetTemplate createCommandGetTemplate(){
        return new CommandGetTemplate();
    }

    public static CommandGetTemplatePrivilegesByUser createCommandGetTemplatePrivilegesByUser(){
        return new CommandGetTemplatePrivilegesByUser();
    }

    public static CommandPostTemplateStatus createCommandPostTemplateStatus(){
        return new CommandPostTemplateStatus();
    }

    public static CommandPostTemplate createCommandPostTemplate(){
        return new CommandPostTemplate();
    }

    public static CommandUpdateTemplate CommandUpdateTemplate(){
        return new CommandUpdateTemplate();
}




}
