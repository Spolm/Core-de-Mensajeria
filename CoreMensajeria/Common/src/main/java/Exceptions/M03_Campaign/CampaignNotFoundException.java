package Exceptions.M03_Campaign;

import Exceptions.PersonalizedException;

public class CampaignNotFoundException extends PersonalizedException {

    public CampaignNotFoundException(){}

    private int idCampaign =-100;
    private String internalMessage = "Internal message no defined";

    public CampaignNotFoundException(Exception e){
        super(e);
    }

    public CampaignNotFoundException(String msg){
        super(msg);
    }

    public CampaignNotFoundException(String msg, Exception e){
        super(msg, e);
    }

    public CampaignNotFoundException(String msg,Exception e, int id ){
        super(msg, e);
        this.idCampaign = id;
    }
    public CampaignNotFoundException(String msg,Exception e, int id ,
                                    String internalMessage){
        super(msg, e);
        this.idCampaign = id;
        this.internalMessage = internalMessage;
    }

    public int getIdCampaign() {
        return idCampaign;
    }


    public String getInternalMessage() {
        return internalMessage;
    }
}