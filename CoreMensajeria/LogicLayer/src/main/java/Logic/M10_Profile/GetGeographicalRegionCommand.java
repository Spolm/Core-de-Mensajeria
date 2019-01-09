package Logic.M10_Profile;

import Entities.M10_Profile.GeographicalRegion;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOGeographicalRegion;
import Persistence.IDAOProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * GetGeographicalRegionCommand Class is responsible for having a region searched in the database by id
 */
public class GetGeographicalRegionCommand extends Command {

    private int id;
    private ArrayList<GeographicalRegion> geographicalRegions;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * CTOR of GetGeographicalRegionCommand
     * @param id id of the geographical region
     */
    public GetGeographicalRegionCommand(int id){
        //region Instrumentation Debug
        log.debug("Entrando al constructor GetGeographicalRegionCommand(" + id +")");
        //endregion
        this.id = id;
    }

    /**
     * Method responsible for having a region searched in the database by id
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOGeographicalRegion dao = factory.createDAOGeographicalRegion();
        geographicalRegions = dao.getGeographicalRegions(id);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() " +
                "GetGeographicalRegionCommand( " + id + ")" + " exitosamente");
        //endregion

        //region Instrumentation Debug
        log.debug("Saliendo metodo execute() de GetGeographicalRegionCommand retornando:" +
                " de GeographicalRegions : " + geographicalRegions.toString());
        //endregion
    }

    /**
     * Method that returns the execution of the command
     * @return object geographicalRegions
     */
    @Override
    public Object Return() {
        return geographicalRegions;
    }
}
