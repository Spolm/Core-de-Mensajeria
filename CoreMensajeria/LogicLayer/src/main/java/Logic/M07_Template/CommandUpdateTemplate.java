package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandUpdateTemplate extends Command {
    private String json;
    private Boolean rest;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandUpdateTemplate(String json) {
        this.json = json;
    }

    public CommandUpdateTemplate() {
    }

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandUpdateTemplate" );
        //endregion
        //TemplateHandler templateHandler = new TemplateHandler();
        //rest = templateHandler.updateTemplateData(json);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        rest = daoTemplate.updateTemplateData(json);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandUpdateTemplate" );
        //endregion
    }

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
