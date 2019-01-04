package Factory;

import Mappers.CampaignMapper.MapperCampaignWithOut_Company;
import Mappers.CampaignMapper.MapperCampaignWithOut_id_And_Company;
import Mappers.CampaignMapper.MapperFullCampaign;
import Mappers.CompanyMapper.*;

public class MapperFactory {

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

    /**
     * Metodos que instancian un obejto del tipo MapperFullCampaign
     * @return un objeto del tipo MapperFullCampaign
     */
    public static MapperFullCampaign CreateMapperFullCampaign(){

        return new MapperFullCampaign();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperCampaignWithOut_Company
     * @return un objeto del tipo MapperCampaignWithOut_Company
     */
    public static MapperCampaignWithOut_Company CreateMapperCampaignWithOut_Company(){

        return new MapperCampaignWithOut_Company();
    }

    /**
     * Metodos que instancian un obejto del tipo MapperCampaignWithOut_id_And_Company
     * @return un objeto del tipo MapperCampaignWithOut_id_And_Company
     */
    public static MapperCampaignWithOut_id_And_Company CreateMapperCampaignWithOut_id_And_Company(){

        return new MapperCampaignWithOut_id_And_Company();
    }


    public static MapperIdCompany createMapperIdCompany(){
        return  new MapperIdCompany();
    }

    public static MapperIdStatusCompany createMapperIdStatusCompany(){
        return  new MapperIdStatusCompany();
    }

}
