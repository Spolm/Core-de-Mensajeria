package Logic.M10_Profile;

import Entities.M10_Profile.Responsability;
import Logic.Command;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.IDAOProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GetResponsabilityByCompanyCommand Class is responsible for looking for resposibilities by company
 */
public class GetResponsabilityByCompanyCommand extends Command {
    private int companyId;
    private Responsability responsability;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    /**
     * CTOR GetResponsabilityByCompanyCommand
     * @param companyId id of company
     */
    public GetResponsabilityByCompanyCommand(int companyId){
        //region Instrumentation Debug
        log.debug("Entrando al constructor GetResponsabilityByCompanyCommand(" + companyId +")");
        //endregion
        this.companyId = companyId;
    }

    /**
     * Method is responsible for looking for resposibilities by company
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        DAOAbstractFactory factory = DAOAbstractFactory.getFactory();
        IDAOProfile dao = factory.createDAOProfile();
        responsability = dao.getResponsability(companyId);

        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() " +
                "GetResponsabilityByCompanyCommand( " + companyId + ")" + " exitosamente");
        //endregion

        //region Instrumentation Debug
        log.debug("Saliendo metodo execute() de GetResponsabilityByCompanyCommand retornando:" +
                " de Responsability : " + responsability.toString());
        //endregion
    }

    /**
     * Method that returns the execution of the command
     * @return Responsability
     */
    @Override
    public Object Return() {
        return responsability;
    }
}
