package Classes.M07_Template;

import Classes.M03_Campaign.Campaign;
import Classes.M04_Channel_Integrator.ChannelPackage.Channel;
import Classes.M07_Template.MessagePackage.*;
import Classes.M07_Template.StatusPackage.*;

import java.util.*;


public class Template {
    private Message message;
    private String creationDate;
    private int templateId;
    private Status status;
    private ArrayList<Channel> channels;
    private Campaign campaign;
    //private Origin origin;

    public Template() {}

    public Template(int templateId) {
        this.templateId = templateId;
    }

    public Template(Message message, Status status, String creationDate, int templateId) {
        this.message = message;
        this.status = status;
        this.creationDate = creationDate;
        this.templateId = templateId;
    }

    public int getTemplateId(){
        return templateId;
    }

    public void setTemplateId(int templateId){
        this.templateId = templateId;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) { this.status = status; }

    public Message getMessage(){ return message; }

    public void setMessage(Message message) { this.message = message; }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public void addChannel(Channel channel){
        channels.add(channel);
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template template = (Template) o;
        return getTemplateId() == template.getTemplateId() &&
                Objects.equals(message, template.message) &&
                Objects.equals(getCreationDate(), template.getCreationDate()) &&
                Objects.equals(status, template.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, getCreationDate(), getTemplateId(), status);
    }

}

