package webService.M07_Template;

import DTO.M07_Template.DTOTemplate;
import Entities.Entity;
import Entities.M01_Login.Privilege;
import Entities.M07_Template.HandlerPackage.StatusHandler;
import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M07_Template.CommandGetTemplate;
import Logic.M07_Template.CommandGetTemplates;
import Mappers.M07_Template.MapperTemplate;
import Mappers.MapperFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webService.M01_Login.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * M07_Template es un API que es responsable de pedir informacion acerca de las plantillas.
 */

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Template {
    private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    private final String MESSAGE_ERROR_PARAMETERDOESNTEXIST= "El parámetro ingresado no existe";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    final static Logger log = LogManager.getLogger("CoreMensajeria");


    /**
     * Metodo que devuelve todas las plantillas filtradas por usuario y su compania.
     * @param userId id del usuario
     * @param companyId id of the company
     * @return ArrayList of templates
     */
    @GET
    public Response getTemplates(@QueryParam("userId") int userId,
                                 @QueryParam("companyId") int companyId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getTemplates("+userId+","+companyId+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(companyId==0 || userId==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandGetTemplates(userId,companyId);
            c.execute();
            MapperTemplate mapperTemplate = MapperFactory.createMapperTemplate();
            ArrayList<DTOTemplate> dtoTemplates = (ArrayList<DTOTemplate>) mapperTemplate.CreateDtoList(((CommandGetTemplates) c).Return());
            response = Response.ok(gson.toJson(dtoTemplates)).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getTemplates("+userId+","+companyId+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplates("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplates("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplates("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getTemplates("+userId+","+companyId+") con retorno: "+ response.getEntity().toString());
        //endregion
        return response;
    }

    /**
     * Este metodo retorna una plantilla especifica.
     * @param id id de la template que se pidio.
     * @return template
     */
    @GET
    @Path("/{templateId}")//Subsequent Path
    public Response getTemplate(@PathParam("templateId") int id){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getTemplate("+id+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(id==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandGetTemplate(id);
            c.execute();
            MapperTemplate mapperTemplate = MapperFactory.createMapperTemplate();
            DTOTemplate dtoTemplate = mapperTemplate.CreateDto(((CommandGetTemplate) c).Return());
            response = Response.ok(gson.toJson(dtoTemplate)).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getTemplate() exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplate("+id+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplate("+id+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplate("+id+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo getTemplate("+id+") con retorno: "+ response.getEntity().toString());
        //endregion

        return response;
    }

    /**
     * Este metodo retorna los privilegios que un usuario tiene sobre una plantilla.
     * @param userId id of the user
     * @param companyId id of the company
     * @return
     */
    @GET
    @Path("/privileges")
    public Response getTemplatePrivilegesByUser(@QueryParam("userId") int userId,
                                                @QueryParam("companyId") int companyId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo getTemplate("+userId+","+companyId+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(userId==0||companyId==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandGetTemplatePrivilegesByUser(userId,companyId);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo getTemplatePrivilegesByUser("+userId+","+companyId+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplatePrivilegesByUser("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplatePrivilegesByUser("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo getTemplatePrivilegesByUser("+userId+","+companyId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        return response;
    }

    /**
     *
     * Este metodo es responsable de actualizar el estatus de una plantilla en especifico.
     * @param templateId id of the template
     * @param userId id of the user
     * @return If the template was saved successfully it returns true,
     * otherwise it returns false.
     */
    @POST
    @Path("/update/{templateId}")//Subsequent Path
    public Response postTemplateStatus(@PathParam("templateId") int templateId, int userId){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo postTemplateStatus("+templateId+","+userId+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(userId==0||templateId==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandPostTemplateStatus(templateId,userId);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo postTemplateStatus("+templateId+","+userId+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplateStatus("+templateId+","+userId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplateStatus("+templateId+","+userId+") arrojo la excepcion:" + e.getMessage());
            //endregion

        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplateStatus("+templateId+","+userId+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        return response;
    }

    //TODO: Arreglar los parametros y retornos de estos metodos
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTemplate(String json){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo postTemplate("+json+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(json==null){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandPostTemplate(json);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo postTemplate("+json+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo postTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        return response;
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTemplate(String json){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo updateTemplate("+json+")" );
        //endregion
        Response response;
        Error error;
        try {
            if(json==null){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandUpdateTemplate(json);
            c.execute();
            response = Response.ok(gson.toJson(c.Return())).build();
            //region Instrumentation Info
            log.info("Se ejecuto el metodo updateTemplate("+json+") exitosamente");
            //endregion
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo updateTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo updateTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
            //region Instrumentation Error
            log.error("El metodo updateTemplate("+json+") arrojo la excepcion:" + e.getMessage());
            //endregion
        }
        return response;
    }
}
