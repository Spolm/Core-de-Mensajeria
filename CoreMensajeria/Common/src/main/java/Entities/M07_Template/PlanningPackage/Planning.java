package Entities.M07_Template.PlanningPackage;

import Entities.Entity;

import java.sql.Date;
import java.util.Objects;

public class Planning extends Entity {

    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    private int idPlanning;

    public Planning() {
    }

    public Planning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public Planning(Date startDate, Date endDate, String startTime, String endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Planning(Date startDate, Date endDate, String startTime, String endTime, int idPlanning) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idPlanning = idPlanning;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planning)) return false;
        Planning planning = (Planning) o;
        return getIdPlanning() == planning.getIdPlanning() &&
                Objects.equals(getStartDate(), planning.getStartDate()) &&
                Objects.equals(getEndDate(), planning.getEndDate()) &&
                Objects.equals(getStartTime(), planning.getStartTime()) &&
                Objects.equals(getEndTime(), planning.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate(), getStartTime(), getEndTime(), getIdPlanning());
    }
}

