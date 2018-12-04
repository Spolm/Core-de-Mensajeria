package Classes.M07_Template.StatusPackage;

/**
 * Status abstract class uses for storing status information from a template
 */
public abstract class Status {
    /**
     * id of the status
     */
    private int statusId;
    /**
     * name of the status
     */
    private String statusName;

    /**
     * empty builder
     */
    public Status(){}

    /**
     * builder with the id and name of the estatus
     * @param statusId  id of the estatus
     * @param statusName name of the estatus
     */
    public Status(int statusId, String statusName){
        this.statusId = statusId;
        this.statusName = statusName;
    }

    /**
     * method that is responsible for creating a specified status (approved or not approved)
     * @param statusId  id of the status
     * @param statusName name of the status
     * @return new status
     */
    public static Status createStatus(int statusId,String statusName){
        Status status;
        if(statusName.equals("Aprobado")){
            status = new ApprovedStatus(statusId,statusName);
        }else if(statusName.equals("No Aprobado")){
            status = new NotApprovedStatus(statusId,statusName);
        }else{
            status = null;
        }
        return status;
    }

    /**
     * show the name of the status
     * @return name of the status
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * add/modify the name of the status
     * @param statusName new name
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * show the id of the status
     * @return  id of the status
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * add of the id of the status
     * @param statusId  new id of the status
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
