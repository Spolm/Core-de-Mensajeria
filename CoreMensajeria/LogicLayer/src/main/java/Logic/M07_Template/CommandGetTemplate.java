package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandGetTemplate extends Command {

    private int templateId;
    private Template template;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandGetTemplate(int templateId){
        this.templateId = templateId;
    }

    public CommandGetTemplate() {
    }

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandGetTemplate" );
        //endregion
        //TemplateHandler templateHandler = new TemplateHandler();
        //template = templateHandler.getTemplate(templateId);
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        template = (Template) daoTemplate.get(templateId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplate" );
        //endregion
    }

    @Override
    public Template Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandGetTemplate" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplate" );
        //endregion
        return template;
    }

}
