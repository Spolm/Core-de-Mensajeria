package Logic;

import Entities.Entity;
import DTO.M07_Template.NewParameter;
import Logic.M01_Login.GetUser;
import Logic.M07_Template.*;
import Logic.M09_Statistics.*;
import Logic.M02_Company.AddCompanyCommand;

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

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }
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

    public static CommandPostTemplate createCommandPostTemplate(){
        return new CommandPostTemplate();
    }

    public static CommandUpdateTemplate CommandUpdateTemplate(){
        return new CommandUpdateTemplate();
}




}
