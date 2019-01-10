package Logic.M07_Template;

import Entities.Entity;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Parameter;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M07_Template.DAOParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * CommandGetParameters es una clase que permite obtener todos los parametros.
 */

public class CommandGetParameters extends Command {
    private int companyId;
    private ArrayList<Parameter> parameterList;
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    public CommandGetParameters(int companyId) {
        this.companyId = companyId;
    }

    public CommandGetParameters() {
    }

    /**
     * El metodo execute() es aquel donde se ejecuta la funcion principal de la clase
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo execute() de CommandGetParameters" );
        //endregion
        DAOParameter daoParameter = DAOFactory.instaciateDaoParameter();
        parameterList = daoParameter.getParameters(companyId);
        //region Instrumentation Info
        log.info("Se ejecuto el metodo execute() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo execute() de CommandGetParameters" );
        //endregion
    }

    /**
     * El metodo Return() retorna una lista de parametros.
     * @return
     */
    @Override
    public ArrayList<Parameter> Return() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo return() de CommandGetParameters" );
        //endregion
        //region Instrumentation Info
        log.info("Se ejecuto el metodo Return() exitosamente");
        //endregion
        //region Instrumentation Debug
        log.debug("Saliendo de el metodo Return() de CommandGetParameters" );
        //endregion
        return parameterList;
    }

}
