package Logic.M07_Template;

import DTO.DTOFactory;
import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import DTO.M07_Template.NewParameter;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CommandPostParameter es una clase que permite insertar un parametro nuevo.
 */
public class CommandPostParameter extends Command {

    private NewParameter newParameter;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandPostParameter(NewParameter newParameter){
        this.newParameter = newParameter;
    }

    public CommandPostParameter() {
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandPostParameter" );
        //endregion
        DAOParameter daoParameter = DAOFactory.instaciateDaoParameter();
        daoParameter.postParameter(newParameter.getName(),newParameter.getCompanyId());
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandPostParameter" );
        //endregion
    }

    @Override
    public Entity Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo Return() de CommandPostParameter" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandPostParameter" );
        //endregion

        return null;
    }

}
