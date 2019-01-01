package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M09_Statistics.*;
import Logic.M02_Company.AddCompanyCommand;

import java.util.List;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static GetAllCompaniesByUserCommand getAllCompaniesByUserCommand(Integer userId) {return new GetAllCompaniesByUserCommand(userId); }
    public static GetCampaignsForCompanyCommand getCampaignsForCompanyCommand(List<Integer> companyIds){return new GetCampaignsForCompanyCommand(companyIds);}
    public static GetAllChannelsCommand getAllChannelsCommand() {return new GetAllChannelsCommand();}
    public static GetIntegratorsForChannelCommand getIntegratorsForChannelCommand(List<Integer> channelIds){ return new GetIntegratorsForChannelCommand(channelIds);}
    public static Command getCompanyStatisticCommand(){ return new GetCompanyStatisticCommand();}
    public static Command getCampaignStatisticCommand(){ return new GetCampaignStatisticCommand();}
    public static Command getChannelStatisticCommand(){ return new GetChannelStatisticCommand();}
    public static Command getIntegratorStatisticCommand(){ return new GetIntegratorStatisticCommand();}

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }



}
