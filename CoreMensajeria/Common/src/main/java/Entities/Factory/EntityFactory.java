package Entities.Factory;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M09_Statistics.Statistics;

import java.util.ArrayList;
import java.util.Date;

public class EntityFactory {

    /**
     * Fabrica para compañias sin id y sin link
     ** @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Company con los parametros name, desc, status
     */

    public static Company CreateCompanyWithOutID(String name, String desc, boolean status){
        return new Company(name, desc, status);
    }
    /**
     * Fabrica para compañias sin link
     * @param id id de una Compañia
     * @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @return un objeto del tipo Company con los parametros id, name, desc, status
     */
    public static Company CreateCompanyWithOutLink(int id, String name, String desc, boolean status){
        return new Company(id, name, desc, status);
    }
    /**
     * Fabrica para compañias con todos los atributos
     * @param id id de una Compañia
     * @param name nombre de la compañia
     * @param desc descripcion de la compañia
     * @param status el estatus de la compañia, puede ser activada o desactivada
     * @param link el enlace de la compañia
     * @return un objeto del tipo Company con los parametros id, name, desc, status, link
     */
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
     * @return un objeto del tipo Campaign con los parametros nameCampaign, descCampaign, statusCampaign, startCampaign
     * endCampaign
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
     * @return un objeto del tipo Campaign con los parametros idCampaign, nameCampaign, descCampaign, statusCampaign,
     * startCampaign, endCampaign
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
     * @return un objeto del tipo Campaign con los parametros idCampaign, nameCampaign, descCampaign, statusCampaign,
     * startCampaign, endCampaign, company
     */

    public static Campaign CreateFullCampaign(int idCampaign, String nameCampaign, String descCampaign,
                                              boolean statusCampaign, Date startCampaign,
                                              Date endCampaign, Company company){

        return new Campaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                            endCampaign, company);

    }

    /**
     * Fabrica para estadisticas sin atributos
     * @return un objeto del tipo Statistic sin datos
     */

    public static Statistics createStatistic(){ return new Statistics(); }

    /**
     * Fabrica para estadisticas
     * @param x una lista para el eje que indica el tipo de datos de la estadistica
     * @param y una lista para los valores correspondientes a cada dato de x
     * @return un objeto del tipo Statistic con sus valores fijados
     */

    public static Statistics createStatistic(ArrayList x, ArrayList<Integer> y){ return new Statistics(x,y); }

}
