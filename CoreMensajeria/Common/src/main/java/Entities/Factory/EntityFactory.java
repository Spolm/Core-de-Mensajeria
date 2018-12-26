package Entities.Factory;

import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;

import java.util.Date;

public class EntityFactory {



    public static Company CreateCompanyWithOutID(String name, String desc, boolean status){
        return new Company(name, desc, status);
    }

    public static Company CreateCompanyWithOutLink(int id, String name, String desc, boolean status){
        return new Company(id, name,desc,status);
    }

    public static Company CreateFullCompany(int id, String name, String desc, boolean status, String link){
        return new Company(id, name, desc, status, link);
    }
    /**
     * Fabrica para campañas sin id y sin Company
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     */
    public static Campaign CreateCampaignWithOut_ID_Company(String nameCampaign, String descCampaign,
                                                            boolean statusCampaign, Date startCampaign,
                                                            Date endCampaign){

        return new Campaign(nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign);
    }

    /**
     * Fabrica para campañas sin Company
     * @param idCampaign id de una campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     */

    public static Campaign CreateCampaignWithOut_Company(int idCampaign, String nameCampaign, String descCampaign,
                                                         boolean statusCampaign, Date startCampaign, Date endCampaign){

        return new Campaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign, endCampaign);
    }

    /**
     * Fabrica para campañas con todos los atributos
     * @param idCampaign id de una campaña
     * @param nameCampaign nombre para una campaña recien creada
     * @param descCampaign descripcion de la campaña
     * @param statusCampaign el estatus de la campaña, puede ser activada o desactivada
     * @param startCampaign fecha de inicio de la campaña
     * @param endCampaign fecha de finalización de la campaña
     * @param company objeto de tipo Company
     */

    public static Campaign CreateFullCampaign(int idCampaign, String nameCampaign, String descCampaign,
                                              boolean statusCampaign, Date startCampaign,
                                              Date endCampaign, Company company){

        return new Campaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                            endCampaign, company);

    }
}
