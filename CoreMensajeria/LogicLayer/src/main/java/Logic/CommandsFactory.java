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

    public static Command GetAllCompaniesByUser(Integer userId) {return new getAllCompanies(userId); }
    public static Command getCampaignsForCompany(List<Integer> companyIds){return new getCampaignsForCompany(companyIds);}

    public static AddCompanyCommand createAddCompanyCommand( Entity _co ){ return new AddCompanyCommand( _co ); }



}
