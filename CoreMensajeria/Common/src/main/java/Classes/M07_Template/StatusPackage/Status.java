package Classes.M07_Template.StatusPackage;

public abstract class Status {
    private int statusId;
    private String statusName;

    public Status(){}

    public Status(int statusId, String statusName){
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
