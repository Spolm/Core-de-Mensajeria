package Exceptions.M03_Campaign;

import Exceptions.PersonalizedException;

public class CampaignInvalidDataException extends PersonalizedException {

    public CampaignInvalidDataException(){}

    private String internalMessage = "Internal message no defined";

    public CampaignInvalidDataException(Exception e){
        super(e);
    }

    public CampaignInvalidDataException(String msg){
        super(msg);
    }

    public CampaignInvalidDataException(String msg, Exception e){
        super(msg, e);
    }

    public CampaignInvalidDataException(String msg, Exception e,
                                       String internalMessage){
        super(msg, e);

        this.internalMessage = internalMessage;
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}