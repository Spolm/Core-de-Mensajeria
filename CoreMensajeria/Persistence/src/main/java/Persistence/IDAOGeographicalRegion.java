package Persistence;

import Entities.M10_Profile.GeographicalRegion;

import java.util.ArrayList;

public interface IDAOGeographicalRegion extends IDAO {
    public ArrayList<GeographicalRegion> getGeographicalRegions(int id);
}
