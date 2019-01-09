package Logic.M07_Template;

import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CommandGetTemplatePrivilegesByUser extends Command {

    private int userId;
    private int companyId;
    private ArrayList<Privilege> privileges;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandGetTemplatePrivilegesByUser(int userId, int companyId) {
        this.userId = userId;
        this.companyId = companyId;
    }

    public CommandGetTemplatePrivilegesByUser() {
    }

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute de CommandGetTemplatePrivilegesByUser" );
        //endregion
        //TemplateHandler templateHandler = new TemplateHandler();
        //privileges = templateHandler.getTemplatePrivilegesByUser(userId,companyId);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        privileges = daoTemplate.getTemplatePrivilegesByUser(userId,companyId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandGetTemplatePrivilegesByUser" );
        //endregion
    }

    @Override
    public ArrayList<Privilege> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandGetTemplatePrivilegesByUser" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplatePrivilegesByUser" );
        //endregion
        return privileges;
    }

}
