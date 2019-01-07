package DTO;

import DTO.M02_DTO.*;
import DTO.M03_DTO.*;
import DTO.M04_Integrator.DTOIntegrator;
import DTO.M09_DTO.DTOStatistic;
import Entities.M02_Company.Company;
import DTO.M04_Integrator.DTOIntegrator;
import java.util.ArrayList;
import java.util.Date;

/**
 * Fabrica que instancia todos los dto
 */
public class DTOFactory {

  //// compañias y campañas
    /**
     * Metodos que instancian un obejto del tipo DTOIdCompany mediante los parametros pasados
     * @return un objeto del tipo DTOIdCompany
     */
    public static DTOIdCompany CreateDTOIdCompany(int id ){

        return new DTOIdCompany(id);
    }
    /**
     * Metodos que instancian un obejto del tipo DTOIdCompany mediante los parametros pasados
     * @return un objeto del tipo DTOidStatusCompanyCompany
     */
    public static DTOIdStatusCompany CreateDTOIdStatusCompany( int id, boolean status ){

        return new DTOIdStatusCompany( id, status );
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

    public static DTOCompanyWithOut_Link CreateDtoCompanyWithOut_Link(int id, String name,
                                                                      String desc, boolean status){

        return new DTOCompanyWithOut_Link(id, name, desc, status);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOFullCompany mediante los parametros pasados
     * @return un objeto del tipo DTOFullCompany
     */

     public static DTOFullCompany CreateDtoFullCompany( int id, String name, String desc,
                                                        boolean status, String link, int userId ){

         return new DTOFullCompany( id, name, desc, status, link, userId );
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
     * Metodos que instancian un obejto del tipo DTOIdCampaign mediante los parametros pasados
     * @return un objeto del tipo DTOIdCampaign
     */
    public static DTOIdCampaign CreateDTOIdCampaign(int id ){

        return new DTOIdCampaign( id );
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

    /**
     * Metodos que instancian un obejto del tipo DTOIdStatusCampaign mediante los parametros pasados
     * @return un objeto del tipo DTOIdStatusCampaign
     */
    public static DTOIdStatusCampaign CreateDTOIdStatusCampaign( int id, boolean status ){

        return new DTOIdStatusCampaign( id, status );
    }

/////end





// M04_region
    public static DTOIntegrator CreateDTOIntegrator (int threadCapacity, float messageCost,
                                                     String nameIntegrator, String apiIntegrator, boolean enabled){
        return new DTOIntegrator(threadCapacity,messageCost,nameIntegrator,apiIntegrator,enabled);
    }
//end region







    /**
     * Metodo que instancia un obejto del tipo DTOStatistic
     * @return un objeto del tipo DTOStatistic
     */


    public static DTOStatistic CreateDTOStatistic(ArrayList x, ArrayList<Integer> y){ return new DTOStatistic(x,y);}




}
