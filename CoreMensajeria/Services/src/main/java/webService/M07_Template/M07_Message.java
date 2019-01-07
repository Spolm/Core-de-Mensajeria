package webService.M07_Template;

import Entities.M07_Template.HandlerPackage.MessageHandler;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Logic.Command;
import Logic.CommandsFactory;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webService.M01_Login.Error;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * M07_Message class is an API that is responsible for requesting information
 * about template messages
 */
@Path("/")
public class M07_Message {
    private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    final static Logger log = LogManager.getLogger("CoreMensajeria");

    Gson gson = new Gson();

    /**
     * this method returns all the templates and each one with
     * its message and associated parameters.
     * @return ArrayList of template with messages and parameters
     */
    @GET
    public Response getMessages(){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getMessages()" );
        //endregion
        Response response;
        Error error;
        try {
            Command c = CommandsFactory.createCommandGetMessages();
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getMessages() exitosamente");
            //endregion
        } catch (Exception e) {
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getMessages() arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getMessages() con retorno: "+ response.getEntity().toString());
        //endregion
        return response;
    }

}
