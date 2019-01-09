package Logic.M07_Template;

import DTO.M07_Template.NewParameter;
import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Logic.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandPostParameters extends Command {

    private NewParameter newParameter;
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    public CommandPostParameters(NewParameter newParameter){
        this.newParameter = newParameter;
    }

    public CommandPostParameters() {
    }

    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandPostParameters" );
        //endregion
        ParameterHandler parameterHandler = new ParameterHandler();
        parameterHandler.postParameter(newParameter.getName(),newParameter.getCompanyId());
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandPostParameters" );
        //endregion
    }

    @Override
    public Entity Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandPostParameters" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandPostParameters" );
        //endregion
        return null;
    }

}
