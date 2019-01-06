package Entities;

import Entities.M01_Login.User;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.ApprovedStatus;
import Entities.M07_Template.StatusPackage.NotApprovedStatus;
import Entities.M07_Template.StatusPackage.Status;
import Entities.M08_Validation.SentMessage;

import java.sql.Date;
import java.util.ArrayList;

public class EntityFactory {

    static public Entity user(int id, String _passwordUser, String _usernameUser, int _typeUser, String _emailUser, String _phoneUser, Date _dayOfBirthUser, String _countryUser, String _addressUser, String _genreUser, String _cityUser ) {
        return new User(id, _passwordUser, _usernameUser, _typeUser, _emailUser, _phoneUser, _dayOfBirthUser, _countryUser, _addressUser, _genreUser, _cityUser);
    }

    static public Entity user() {
        return new User();
    }


    // M07_Templates
    public static Parameter CreateParameter(int parameterId, String name, String description){
        return new Parameter(parameterId,name,description);
    }

    public static Message CreateMessage(int messageId, ArrayList<Parameter> parameterArrayList, String message){
        return new Message(messageId, parameterArrayList, message);
    }

    public static Planning CreatePlanning(String startDate, String endDate, String startTime, String endTime, int idPlanning){
        return new Planning(startDate, endDate, startTime, endTime, idPlanning);
    }

    public static ApprovedStatus CreateApprovedStatus(int statusId,String statusName){
        return new ApprovedStatus(statusId, statusName);
    }

    public static NotApprovedStatus CreateNotApprovedStatus(int statusId, String statusName){
        return new NotApprovedStatus(statusId, statusName);
    }



    //region M_08
    public static Entity createSendMessage() {
        return new SentMessage();
    }
}
