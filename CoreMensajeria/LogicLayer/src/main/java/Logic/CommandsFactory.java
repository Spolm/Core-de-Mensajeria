package Logic;

import Entities.Entity;
import Logic.M01_Login.GetUser;
import Logic.M02_Company.*;
import Logic.M03_Campaign.GetCampaignCommand;
import Logic.M03_Campaign.AddCampaignCommand;

public class CommandsFactory {

    public static Command instanciateGetUser ( Entity user) {
        return new GetUser(user);
    }

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }

    public static ChangeStatusCommand createChangeStatusCommand(Entity _co ) {return new ChangeStatusCommand( _co );}

    public static GetAllCompaniesCommand createGetAllCompaniesCommand() {return new GetAllCompaniesCommand();}

    public static GetCompanyCommand createGetCompanyCommand(Entity _co){return new GetCompanyCommand(_co);}

    public static UpdateCompanyCommand createUpdateCompanyCommand(Entity _co) {return new UpdateCompanyCommand(_co);}

    public static GetCompanyByUserCommand createGetCompanyByUserCommand(Entity _co ){ return new GetCompanyByUserCommand( _co ); }

    public static GetCampaignCommand createGetCampaignCommand(Entity _ca ){ return new GetCampaignCommand( _ca ); }



}
