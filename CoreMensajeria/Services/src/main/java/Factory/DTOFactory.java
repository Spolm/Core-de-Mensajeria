package Factory;
import DTO.M02_DTO.DTOCompanyWithOutIdAndLink;
import DTO.M02_DTO.DTOCompanyWithOut_Link;
import DTO.M02_DTO.DTOFullCompany;

import DTO.M02_DTO.DTOIdCompany;
import DTO.M03_DTO.DTOFullCampaign;
import DTO.M03_DTO.DTOCampaignWithOut_Company;
import DTO.M03_DTO.DTOFullCampaign;
import DTO.M03_DTO.DTOCampaignWithOut_id_And_Company;
import Entities.M02_Company.Company;

import java.util.Date;

/**
 * Fabrica que instancia todos los dto
 */
public class DTOFactory {


    /**
     * Metodos que instancian un obejto del tipo DTOIdCompany mediante los parametros pasados
     * @return un objeto del tipo DTOIdCompany
     */
    public static DTOIdCompany CreateDTOIdCompany(int id ){

        return new DTOIdCompany(id);
    }


      /**
       * Metodos que instancian un obejto del tipo DTOCompanyWithOutIdAndLink mediante los parametros pasados
       * @return un objeto del tipo DTOCompanyWithOutIdAndLink
       */
    public static DTOCompanyWithOutIdAndLink CreateDtoCompanyWithOut_idAndlink(String name, String desc,
                                                                               boolean status){

        return new DTOCompanyWithOutIdAndLink(name, desc, status);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOCompanyWithOut_Link mediante los parametros pasados
     * @return un objeto del tipo DTOCompanyWithOut_Link
     */

    public static DTOCompanyWithOut_Link CreateDtoCompanyWithOut_Link(int id, String name, String desc, boolean status){

        return new DTOCompanyWithOut_Link(id, name, desc, status);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOFullCompany mediante los parametros pasados
     * @return un objeto del tipo DTOFullCompany
     */

     public static DTOFullCompany CreateDtoFullCompany(int id, String name, String desc, boolean status, String link){

         return new DTOFullCompany(id, name, desc, status, link);
     }


     /**
      * Metodos que instancian un obejto del tipo DTOCampaignWithOut_id_And_Company mediante los parametros pasados
      * @return un objeto del tipo DTOCampaignWithOut_id_And_Company
      */
    public static DTOCampaignWithOut_id_And_Company CreateDtoCampaignWithOut_id_And_Company(String nameCampaign,
                                                                                            String descCampaign,
                                                                                            boolean statusCampaign,
                                                                                            Date startCampaign,
                                                                                            Date endCampaign) {

        return new DTOCampaignWithOut_id_And_Company(nameCampaign, descCampaign, statusCampaign, startCampaign,
                                                     endCampaign);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOCampaignWithOut_Company mediante los parametros pasados
     * @return un objeto del tipo DTOCampaignWithOut_Company
     */
    public static DTOCampaignWithOut_Company CreateDtoCampaignWithOut_Company(int idCampaign, String nameCampaign,
                                                                              String descCampaign,
                                                                              boolean statusCampaign,
                                                                              Date startCampaign, Date endCampaign) {

        return new DTOCampaignWithOut_Company(idCampaign, nameCampaign, descCampaign, statusCampaign,
                                              startCampaign, endCampaign);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOFullCampaign mediante los parametros pasados
     * @return un objeto del tipo DTOFullCampaign
     */

    public static DTOFullCampaign CreateDtoFullCampaign(int idCampaign, String nameCampaign, String descCampaign,
                                                                   boolean statusCampaign, Date startCampaign,
                                                                   Date endCampaign, Company company) {

        return new DTOFullCampaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                                    endCampaign, company);
    }






}
