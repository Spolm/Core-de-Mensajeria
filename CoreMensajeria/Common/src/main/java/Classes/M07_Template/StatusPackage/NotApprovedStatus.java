package Classes.M07_Template.StatusPackage;

/**
 * NotApprovedStatus class uses for storing status generalization (NotApproved)
 * information from a status
 */
public class NotApprovedStatus extends Status{
    /**
     * builder with the id and name of the status (super)
     * @param statusId  id of the estatus
     * @param statusName name of the status
     */
    public NotApprovedStatus(int statusId, String statusName){
        super(statusId,statusName);
    }
}