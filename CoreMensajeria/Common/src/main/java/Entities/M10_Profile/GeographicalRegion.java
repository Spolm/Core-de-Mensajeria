package Entities.M10_Profile;

import Entities.Entity;

public class GeographicalRegion extends Entity {
    private int geographicalRegionId;
    private String geographicalRegionName;
    private int geographicalRegionType;
    private int geographicalRegionFk;

    public GeographicalRegion(){}

    public GeographicalRegion(int geographicalRegionId,
                              String geographicalRegionName,
                              int geographicalRegionType,
                              int geographicalRegionFk){
        this.geographicalRegionId = geographicalRegionId;
        this.geographicalRegionName = geographicalRegionName;
        this.geographicalRegionType = geographicalRegionType;
        this.geographicalRegionFk = geographicalRegionFk;
    }

    public int getGeographicalRegionId() {
        return geographicalRegionId;
    }

    public void setGeographicalRegionId(int geographicalRegionId) {
        this.geographicalRegionId = geographicalRegionId;
    }

    public String getGeographicalRegionName() {
        return geographicalRegionName;
    }

    public void setGeographicalRegionName(String getGeographicalRegionName) {
        this.geographicalRegionName = getGeographicalRegionName;
    }

    public int getGeographicalRegionType() {
        return geographicalRegionType;
    }

    public void setGeographicalRegionType(int getGeographicalRegionType) {
        this.geographicalRegionType = getGeographicalRegionType;
    }

    public int getGeographicalRegion() {
        return geographicalRegionFk;
    }

    public void setGeographicalRegion(int geographicalRegionFk) {
        this.geographicalRegionFk = geographicalRegionFk;
    }
}
