package DTO.M07_Template;

import DTO.DTO;
import Entities.M01_Login.User;
import Entities.M03_Campaign.Campaign;
import Entities.M05_Channel.Channel;
import Entities.M06_DataOrigin.Application;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.StatusPackage.Status;

import java.sql.Date;
import java.util.ArrayList;

public class DTOTemplate extends DTO {

    private Message _tMessage;
    private Date _tCreationDate;
    private int _tTemplateid;
    private Status status;
    private ArrayList<Channel> _tChannels;
    private Campaign _tCampaign;
    private Application _tApplication;
    private User _tUser;
    private Planning _tPlanning;

    public DTOTemplate() {
    }

    public DTOTemplate(Message _tMessage, Date _tCreationDate, int _tTemplateid, Status status, ArrayList<Channel> _tChannels, Campaign _tCampaign, Application _tApplication, User _tUser, Planning _tPlanning) {
        this._tMessage = _tMessage;
        this._tCreationDate = _tCreationDate;
        this._tTemplateid = _tTemplateid;
        this.status = status;
        this._tChannels = _tChannels;
        this._tCampaign = _tCampaign;
        this._tApplication = _tApplication;
        this._tUser = _tUser;
        this._tPlanning = _tPlanning;
    }

    public Message get_tMessage() {
        return _tMessage;
    }

    public void set_tMessage(Message _tMessage) {
        this._tMessage = _tMessage;
    }

    public java.sql.Date get_tCreationDate() {
        return _tCreationDate;
    }

    public void set_tCreationDate(Date _tCreationDate) {
        this._tCreationDate = _tCreationDate;
    }

    public int get_tTemplateid() {
        return _tTemplateid;
    }

    public void set_tTemplateid(int _tTemplateid) {
        this._tTemplateid = _tTemplateid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Channel> get_tChannels() {
        return _tChannels;
    }

    public void set_tChannels(ArrayList<Channel> _tChannels) {
        this._tChannels = _tChannels;
    }

    public Campaign get_tCampaign() {
        return _tCampaign;
    }

    public void set_tCampaign(Campaign _tCampaign) {
        this._tCampaign = _tCampaign;
    }

    public Application get_tApplication() {
        return _tApplication;
    }

    public void set_tApplication(Application _tApplication) {
        this._tApplication = _tApplication;
    }

    public User get_tUser() {
        return _tUser;
    }

    public void set_tUser(User _tUser) {
        this._tUser = _tUser;
    }

    public Planning get_tPlanning() {
        return _tPlanning;
    }

    public void set_tPlanning(Planning _tPlanning) {
        this._tPlanning = _tPlanning;
    }
}
