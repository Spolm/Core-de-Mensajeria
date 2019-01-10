package webService.M07_Template;


import DTO.M07_Template.NewParameter;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.ParameterDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webService.M01_Login.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * M07_Parameter es un API que es responsable de pedir informacion acerca de los parametros de un mensaje.
 * about the parameters of a message
 */
@Path("/parameters")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Parameter {
    private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    private final String MESSAGE_ERROR_PARAMETERDOESNTEXIST= "El parámetro ingresado no existe";
    final static Logger log = LogManager.getLogger("CoreMensajeria");
    Gson gson = new Gson();

    /**
     * Este metodo es responsable de hacer un parametro nuevo asociado con una compania almacenada.
     * @param newParameter new parameter with its name and companyid
     */
    @POST
    @Path("add")
    public Response postParameter(NewParameter newParameter) {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo postParameter("+newParameter+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(newParameter ==null){
                throw new InvalidParameterException();
            }
            if(newParameter.getName() ==null || newParameter.getCompanyId()==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandPostParameter(newParameter);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo postParameter("+newParameter+") exitosamente");
            //endregion

        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postParameter("+newParameter+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postParameter("+newParameter+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo postParameter("+newParameter+") con retorno: "+ response.getEntity().toString());
        //endregion
        return response;
    }

    /**
     * Este metodo retorna todos los parametros filtrados por una compania especifica.
     * @param companyId company id
     * @return ArrayList of the parameters by company
     */
    @GET
    @Path("get")
    public Response getParameters(@QueryParam("companyId") int companyId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getParameters("+companyId+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(companyId==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandGetParameters(companyId);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getParameters("+companyId+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getParameters("+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getParameters("+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getParameters("+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getParameters("+companyId+") con retorno: "+ response.getEntity().toString());
        //endregion
        return response;
    }

}
