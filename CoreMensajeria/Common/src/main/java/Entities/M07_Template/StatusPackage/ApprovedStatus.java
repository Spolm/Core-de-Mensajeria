package Entities.M07_Template.StatusPackage;

/**
 * ApprovedStatus class uses for storing status generalization (Approved)
 * information from a status
 */
public class ApprovedStatus extends Status{
    /**
     * builder with the id and name of the status (super)
     * @param statusId  id of the estatus
     * @param statusName name of the status
     */
    public ApprovedStatus(int statusId,String statusName){
        super(statusId,statusName);
    }
}