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

    private Message message;
    private Date creationDate;
    private int _id;
    private Status status;
    private ArrayList<Channel> channels;
    private Campaign campaign;
    private Application application;
    private User user;
    private Planning planning;
    private int companyId;

    public DTOTemplate() {
    }

    public DTOTemplate(Message message, Date creationDate, int _id, Status status, ArrayList<Channel> channels, Campaign campaign, Application application, User user, Planning planning, int companyId) {
        this.message = message;
        this.creationDate = creationDate;
        this._id = _id;
        this.status = status;
        this.channels = channels;
        this.campaign = campaign;
        this.application = application;
        this.user = user;
        this.planning = planning;
        this.companyId = companyId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public java.sql.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
