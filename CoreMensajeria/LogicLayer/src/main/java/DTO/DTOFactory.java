package DTO;

import DTO.M01_DTO.DTOPrivilege;
import DTO.M01_DTO.DTOUser;
import DTO.M02_DTO.*;
import DTO.M03_DTO.*;
import DTO.M04_Integrator.DTOIntegrator;
import DTO.M05_Channel.DTOChannel;
import DTO.M09_DTO.DTOStatistic;
import Entities.Entity;
import Entities.M02_Company.Company;
import DTO.M04_Integrator.DTOIntegrator;
import DTO.M07_Template.DTOMessage;
import DTO.M07_Template.DTOParameter;
import DTO.M07_Template.DTOTemplate;
import DTO.M09_DTO.DTOStatistic;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Entities.M06_DataOrigin.Application;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.Status;
import java.util.ArrayList;
import java.util.Date;

/**
 * Fabrica que instancia todos los dto
 */
public class DTOFactory {

    //region M01 Login

    /**
     * Metodo que instancia un objeto del tipo DTOUser
     * @param _idUser id del usuario
     * @param _password contraseña
     * @param _username nombre de usuario
     * @param _type tipo de usuario
     * @param _email direccion de correo del usuario
     * @param _phone telefono del usuario
     * @param _dateOfBirth fecha de nacimiento del usuario
     * @param _country pais del usuario
     * @param _address direccion del usuario
     * @param _gender genero del usuario
     * @param _city ciudad del usuario
     * @param _blocked indica si el usuario esta bloqueado en el sistema
     * @param _remainingAttempts indica la cantidad de intentos disponibles para loggearse correctamente antes de que el usuario sea bloqueado
     * @return Objeto de tipo DTOUser
     */
    public static DTOUser CreateDTOUser(int _idUser, String _password, String _username, int _type, String _email,
                                        String _phone, java.sql.Date _dateOfBirth, String _country, String _address,
                                        String _gender, String _city, Integer _blocked, Integer _remainingAttempts) {

        return new DTOUser(_idUser, _password, _username, _type, _email, _phone, _dateOfBirth, _country, _address,
                _gender, _city, _blocked, _remainingAttempts);

    }
    /**
     * Metodo que instancia un objeto del tipo DTOPrivilege
     * @param _idPrivileges id del privilegio
     * @param _codePrivileges codigo del privilegio
     * @param _actionPrivileges acciones que puede realizar el privilegio
     * @return Objeto del tipo DTOPrivilege
     */


    public static DTOPrivilege CreateDTOPrivilege(int _idPrivileges, String _codePrivileges, String _actionPrivileges) {

        return new DTOPrivilege(_idPrivileges,_codePrivileges,_actionPrivileges);

    }

    /**
    END REGION
  /// COMPANY
     */
    /**
     * Metodos que instancian un obejto del tipo DTOIdCompany mediante los parametros pasados
     * @return un objeto del tipo DTOIdCompany
     */
    public static DTOIdCompany CreateDTOIdCompany(int id ){

        return new DTOIdCompany(id);
    }
    /**
     * Metodos que instancian un obejto del tipo DTOIdStatusCompany mediante los parametros pasados
     * @return un objeto del tipo DTOidStatusCompanyCompany
     */
    public static DTOIdStatusCompany CreateDTOIdStatusCompany( int id, boolean status ){

        return new DTOIdStatusCompany( id, status );
    }
    /**
     * Metodos que instancian un obejto del tipo DTOIdCompUser mediante los parametros pasados
     * @return un objeto del tipo DTOIdCompUser
     */
    public static DTOIdCompUser createDTOIdCompUser( int id, int user ){

        return new DTOIdCompUser( id, user );
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
     // END COMPANY

    //CAMPAIGN
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
                                                                      Date endCampaign, int idCompany) {

        return new DTOFullCampaign(idCampaign, nameCampaign, descCampaign, statusCampaign, startCampaign,
                                    endCampaign, idCompany);
    }

    /**
     * Metodos que instancian un obejto del tipo DTOIdStatusCampaign mediante los parametros pasados
     * @return un objeto del tipo DTOIdStatusCampaign
     */
    public static DTOIdStatusCampaign CreateDTOIdStatusCampaign( int id, boolean status ){

        return new DTOIdStatusCampaign( id, status );
    }

//  END CAMPAIGN





// M04_region
    public static DTOIntegrator CreateDTOIntegrator (int threadCapacity, float messageCost,
                                                     String nameIntegrator, String apiIntegrator, boolean enabled){
        return new DTOIntegrator(threadCapacity,messageCost,nameIntegrator,apiIntegrator,enabled);
    }

    public static DTOChannel createDTOChannel (String nameChannel, String descriptionChannel, ArrayList<Entity> integrators){
        return new DTOChannel(nameChannel, descriptionChannel, integrators);
    }
//end region







    /**
     * Metodo que instancia un obejto del tipo DTOStatistic
     * @return un objeto del tipo DTOStatistic
     */


    public static DTOStatistic CreateDTOStatistic(ArrayList x, ArrayList<Integer> y){ return new DTOStatistic(x,y);}

    /**
     *  Metodo que instancia un objeto del tipo DTOParameter
     * @return un objeto del tipo DTOParamater
     */
    public  static DTOParameter CreateDTOParameter(int pid, String _pName, String pdescription){
        return new DTOParameter(pid,_pName,pdescription);
    }

    public static DTOMessage CreateDTOMessage(int _mMessageId, ArrayList<Parameter> _mParameterArrayList, String _mMessage){
        return new DTOMessage(_mMessageId, _mParameterArrayList,_mMessage);
    }


    public static DTOTemplate CreateDTOTemplate(Message message, String creationDate, int templateId, Status status, ArrayList<Channel> channels, Campaign campaign, Application application, User user, Planning planning){
        return  new DTOTemplate(message,creationDate,templateId,status,channels,campaign,application,user,planning);
    }


}
