package Logic.M10_Profile;

import Entities.M10_Profile.GeographicalRegion;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;

import java.util.ArrayList;

public class GetGeographicalRegionCommand extends Command {

    private int id;
    private ArrayList<GeographicalRegion> geographicalRegions;

    public GetGeographicalRegionCommand(int id){
        this.id = id;
    }

    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOGeographicalRegion dao = factory.createDAOGeographicalRegion();
        geographicalRegions = dao.getGeographicalRegions(id);
    }

    @Override
    public Object Return() {
        return geographicalRegions;
    }
}
