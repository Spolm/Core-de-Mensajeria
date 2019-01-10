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

/**
 * CommandGetParameters es una clase que permite obtener una plantilla especifica por su id.
 */

public class CommandGetTemplate extends Command {

    private int templateId;
    private Template template;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandGetTemplate(int templateId){
        this.templateId = templateId;
    }

    public CommandGetTemplate() {
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandGetTemplate" );
        //endregion
        //TemplateHandler templateHandler = new TemplateHandler();
        //template = templateHandler.getTemplate(templateId);
        IDAOTemplate daoTemplate = DAOAbstractFactory.getFactory().createDaoTemplate();
        template = (Template) daoTemplate.get(templateId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetTemplate" );
        //endregion
    }
    /**
     * El metodo Return() retorna la plantilla
     * @return
     */
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
