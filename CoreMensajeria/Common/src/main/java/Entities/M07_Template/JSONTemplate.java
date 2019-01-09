package Entities.M07_Template;

import com.google.gson.JsonArray;

public class JSONTemplate {
    private int templateId;
    private int campaign;
    private int applicationId;
    private int userId;
    private JsonArray newParameters;
    private int company;
    private String message;
    private JsonArray parameters;
    private JsonArray channel_integrator;
    private JsonArray planning;

    public JSONTemplate() {
    }

    public JSONTemplate(int templateId, int campaign, int applicationId, int userId, JsonArray newParameters, int company, String message, JsonArray parameters, JsonArray channel_integrator, JsonArray planning) {
        this.templateId = templateId;
        this.campaign = campaign;
        this.applicationId = applicationId;
        this.userId = userId;
        this.newParameters = newParameters;
        this.company = company;
        this.message = message;
        this.parameters = parameters;
        this.channel_integrator = channel_integrator;
        this.planning = planning;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getCampaign() {
        return campaign;
    }

    public void setCampaign(int campaign) {
        this.campaign = campaign;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public JsonArray getNewParameters() {
        return newParameters;
    }

    public void setNewParameters(JsonArray newParameters) {
        this.newParameters = newParameters;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonArray getParameters() {
        return parameters;
    }

    public void setParameters(JsonArray parameters) {
        this.parameters = parameters;
    }

    public JsonArray getChannel_integrator() {
        return channel_integrator;
    }

    public void setChannel_integrator(JsonArray channel_integrator) {
        this.channel_integrator = channel_integrator;
    }

    public JsonArray getPlanning() {
        return planning;
    }

    public void setPlanning(JsonArray planning) {
        this.planning = planning;
    }
}
