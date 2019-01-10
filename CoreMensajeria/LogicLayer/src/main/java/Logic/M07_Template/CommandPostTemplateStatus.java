package Logic.M07_Template;

import Entities.M07_Template.HandlerPackage.StatusHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.DAOStatus;
import Persistence.M07_Template.IDAOStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CommandPostTemplateStatus es una clase que permite modificar el estatus de una plantilla.
 */
public class CommandPostTemplateStatus extends Command {

    private int templateId;
    private int userId;
    private boolean flag;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandPostTemplateStatus(int templateId, int userId) {
        this.templateId = templateId;
        this.userId = userId;
        this.flag = false;
    }

    public CommandPostTemplateStatus() {
    }
    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandPostTemplateStatus" );
        //endregion
        IDAOStatus daoStatus = DAOAbstractFactory.getFactory().createDAOStatus();
        flag = daoStatus.postTemplateStatusApproved(templateId,userId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandPostTemplateStatus" );
        //endregion
    }

    /**
     * El metodo Return() retorna un entity.
     * @return
     */
    @Override
    public Boolean Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandPostTemplateStatus" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandPostTemplateStatus" );
        //endregion
        return flag;
    }

}
