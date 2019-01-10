package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * CommandPostTemplate es una clase que permite insertar una plantilla nueva.
 */
public class CommandPostTemplate extends Command {

    private String json;
    private Template rest;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandPostTemplate(String json) {
        this.json = json;
    }

    public CommandPostTemplate() {
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandPostTemplate" );
        //endregion
        DAOTemplate daoTemplate = DAOFactory.instaciateDaoTemplate();
        rest = (Template) daoTemplate.postTemplateData(json);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandPostTemplate" );
        //endregion
    }

    /**
     * El metodo Return() retorna un entity.
     * @return
     */
    @Override
    public Entity Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandPostTemplate" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandPostTemplate" );
        //endregion
        return rest;
    }

}
