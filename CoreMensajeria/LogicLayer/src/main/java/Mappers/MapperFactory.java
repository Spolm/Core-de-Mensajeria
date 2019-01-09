package Mappers;

import Mappers.CompanyMapper.*;
import Mappers.CampaignMapper.*;
import Mappers.GenericMapper;
import Mappers.M04_Integrator.MapperIntegrator;
import Mappers.M05_Channel.MapperChannel;
import Mappers.LoginMapper.LoginMapper;
import Mappers.LoginMapper.UserMapper;
import Mappers.GenericMapper;
import Mappers.M07_Template.MapperTemplate;
import Mappers.M10_Profile.MapperEditUser;
import Mappers.StatisticMapper.StatisticMapper;

public class MapperFactory {

// region compañias
	  /**
     * Metodos que instancian un obejto del tipo MapperFullCompany
     * @return un objeto del tipo MapperFullCompany
     */
    public static MapperFullCompany CreateMapperFullCompany(){

        return new MapperFullCompany();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperCompanyWithOut_Link
     * @return un objeto del tipo MapperCompanyWithOut_Link
     */
    public static MapperCompanyWithOut_Link CreateMapperCompanyWithOut_Link(){

        return new MapperCompanyWithOut_Link();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperCompanyWithOutIdAndLink
     * @return un objeto del tipo MapperCompanyWithOutIdAndLink
     */
    public static MapperCompanyWithOutIdAndLink CreateMapperCompanyWithOutIdAndLink(){

        return new MapperCompanyWithOutIdAndLink();
    }
    // endregion


    // region campañas
    /**
     * Metodos que instancian un obejto del tipo MapperFullCampaign
     * @return un objeto del tipo MapperFullCampaign
     */
    public static MapperFullCampaign CreateMapperFullCampaign(){

        return new MapperFullCampaign();
    }


    /**
     * Metodos que instancian un obejto del tipo MapperIdCompany
     * @return un objeto del tipo MapperIdCompany
     */
    public static MapperIdCompany createMapperIdCompany(){

        return new MapperIdCompany();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperIdStatusCompany
     * @return un objeto del tipo MapperIdStatusCompany
     */
    public static MapperIdStatusCompany createMapperIdStatusCompany(){

        return  new MapperIdStatusCompany();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperIdCompUser
     * @return un objeto del tipo MapperIdCompUser
     */
    public static MapperIdCompUser createMapperIdCompUser(){

        return  new MapperIdCompUser();
    }


    /**
     * Metodos que instancian un obejto del tipo MapperCampaignWithOut_Company
     * @return un objeto del tipo MapperCampaignWithOut_Company
     */
    public static MapperCampaignWithOut_Company CreateMapperCampaignWithOut_Company(){

        return new MapperCampaignWithOut_Company();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperIdCampaign
     * @return un objeto del tipo MapperIdCampaign
     */
    public static MapperIdCampaign createMapperIdCampaign(){

        return  new MapperIdCampaign();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperCampaignWithOut_id_And_Company
     * @return un objeto del tipo MapperCampaignWithOut_id_And_Company
     */
    public static MapperCampaignWithOut_id_And_Company CreateMapperCampaignWithOut_id_And_Company(){

        return new MapperCampaignWithOut_id_And_Company();
    }


    /**
     * Metodos que instancian un obejto del tipo MapperIdStatusCampaign
     * @return un objeto del tipo MapperIdStatusCampaign
     */
    public static MapperIdStatusCampaign createMapperIdStatusCampaign(){

        return new MapperIdStatusCampaign();
    }
    // endregion


    //M04_Region
    public static MapperIntegrator createMapperIntegrator(){return new MapperIntegrator();}
    public static MapperChannel createMapperChannel(){ return new MapperChannel(); }
    // end M04_region


    public static GenericMapper createStatisticMapper(){ return new StatisticMapper();}

    // M07_Template
    public static MapperTemplate createMapperTemplate(){ return new MapperTemplate();}

    public static UserMapper createUserMapper() {
        return new UserMapper();
    }
    public static LoginMapper createLoginMapper() {
        return new LoginMapper();
    }

    //region M10

    /**
     * Method that instantiated a MapperEditUser object
     * @return object MapperEditUser
     */
    public static MapperEditUser createMapperEditUser(){return new MapperEditUser();}
    //endregion
}
