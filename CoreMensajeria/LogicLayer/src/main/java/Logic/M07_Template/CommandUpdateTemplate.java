package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.Factory.DAOAbstractFactory;
import Persistence.M07_Template.DAOTemplate;
import Persistence.M07_Template.IDAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * CommandUpdateTemplate es una clase que permite modificar una plantilla.
 */
public class CommandUpdateTemplate extends Command {
    private String json;
    private Boolean rest;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandUpdateTemplate(String json) {
        this.json = json;
    }

    public CommandUpdateTemplate() {
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandUpdateTemplate" );
        //endregion
        IDAOTemplate daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();
        rest = daoTemplate.updateTemplateData(json);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandUpdateTemplate" );
        //endregion
    }
    /**
     * El metodo Return() retorna un booleano.
     * @return
     */
    @Override
    public Boolean Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandUpdateTemplate" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandUpdateTemplate" );
        //endregion
        return rest;
    }

}
