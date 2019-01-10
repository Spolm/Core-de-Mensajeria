package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.DAOTemplate;
import Persistence.M07_Template.IDAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * CommandGetTemplates es una clase que obtiene todas las plantillas
 */

public class CommandGetTemplates extends Command {

    private int userId;
    private int companyId;
    ArrayList<Template> templateArrayList;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandGetTemplates(int userId, int companyId) {
        this.userId = userId;
        this.companyId = companyId;
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandGetTemplates" );
        //endregion
        IDAOTemplate daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();
        templateArrayList = daoTemplate.getTemplatesByCampaign(userId,companyId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplates" );
        //endregion
    }

    /**
     * El metodo Return() retorna la lista de plantillas.
     * @return
     */
    @Override
    public ArrayList<Template> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo Return() de CommandGetTemplates" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplates" );
        //endregion
        return templateArrayList;
    }

}
