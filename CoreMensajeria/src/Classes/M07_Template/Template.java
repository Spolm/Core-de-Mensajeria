package Classes.M07_Template;

import Classes.M07_Template.MessagePackage.*;
import Classes.M07_Template.StatusPackage.*;

import java.util.*;


public class Template {
    private Message message;
    private String creationDate;
    private int templateId;
    private IStatus status;

    public Template() {}

    public Template(Message message, IStatus status, String creationDate, int templateId) {
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

    public String getStatus() {
        return status.getIStatusTemplate();
    }

    public void setStatus(IStatus status) { this.status = status; }

    public Message getMessage(){ return message; }

    public void setMessage(Message message) { this.message = message; }

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

