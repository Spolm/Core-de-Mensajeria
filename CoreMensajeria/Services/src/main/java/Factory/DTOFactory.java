package Factory;
import DTO.M02_DTO.DTOCompany;
import DTO.M03_DTO.DTOCampaign;
import Entities.M02_Company.Company;

import java.util.Date;

/**
 * Fabrica que instancia todos los dto
 */
public class DTOFactory {

      /**
       * Metodos que instancian un obejto del tipo DTOCompany mediante los parametros pasados
       * @return un objeto del tipo DTOCompany
       */
    public static DTOCompany CreateDtoCompanyWithOut_idAndlink(String name, String desc, boolean status){

        return new DTOCompany(name, desc, status);
    }

    public static DTOCompany CreateDtoCompanyWithOut_Link(int id, String name, String desc, boolean status){

        return new DTOCompany(id, name, desc, status);
    }

     public static DTOCompany CreateDtoFullCompany(int id, String name, String desc, boolean status, String link){

         return new DTOCompany(id, name, desc, status, link);
     }


     /**
      * Metodos que instancian un obejto del tipo DTOCampaign mediante los parametros pasados
      * @return un objeto del tipo DTOCampaign
      */
    public static DTOCampaign CreateDtoCampaignWithOut_id_And_Company(String nameCampaign, String descCampaign,
                                                                      boolean statusCampaign, Date startCampaign,
                                                                      Date endCampaign) {

        return new DTOCampaign(nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign);
    }

    public static DTOCampaign CreateDtoCampaignWithOut_Company(int idCampaign, String nameCampaign, String descCampaign,
                                                               boolean statusCampaign, Date startCampaign,
                                                               Date endCampaign) {

        return new DTOCampaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign);
    }

    public static DTOCampaign CreateDtoCampaignWithOut_Company(int idCampaign, String nameCampaign, String descCampaign,
                                                               boolean statusCampaign, Date startCampaign,
                                                               Date endCampaign, Company company) {

        return new DTOCampaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                               endCampaign, company);
    }






}
