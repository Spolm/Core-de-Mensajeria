package webService.M08_MessageCoreManagement;


import DTO.M08_DTO.ParametersDTO;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.*;
import Exceptions.M08_SendMessageManager.DateNotValidException;
import Exceptions.M08_SendMessageManager.SMSTooLongException;
import Exceptions.M08_SendMessageManager.TemplateNotApprovedException;
import Logic.Command;
import Logic.CommandsFactory;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Logic.M07_Template.CommandGetTemplate;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Servicios para validar mensajes
 */
@Path("/M08_MessageCore")
public class M08_MessageValidation {

    /**
     * @param dto los parametros del mensaje a ser enviado
     * @return el body ingresado si el mismo se valida correctamente
     * @throws PersonalizedException excepciones personalizadas a traves de la WebApplicationException
     * excepcion de javaEE
     */
    @POST
    @Path("/CommandSendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ParametersDTO sendMessage(@Valid ParametersDTO dto) throws WebApplicationException{
        Error error = null;
        ParametersDTO response = dto;
        try {
            CommandGetTemplate commandTemplate = CommandsFactory.createCommandGetTemplate(dto.get_idTemplate());
            commandTemplate.execute();
            Template t = commandTemplate.Return();
            VerifiedParameter parameters = new VerifiedParameter(dto.get_verifiedMessages(),t);
            Command<Boolean> commandValidate = CommandsFactory.createCommandValidate(parameters);
            commandValidate.execute();
            if (commandValidate.Return() == true) {
                    //Codigo cuando es exitoso
                Command commandSchedule = CommandsFactory.createScheduleMessage(parameters);
                commandSchedule.execute();
            }
        } catch (TemplateDoesntExistsException e) {
            error = new Error(e.getMessage());
        } catch (SMSTooLongException e) {
            error = new Error(e.getMessage());
        } catch (TemplateNotApprovedException e) {
            error = new Error(e.getMessage());
        } catch (DateNotValidException e){
            error = new Error(e.getMessage());
        } catch (UnexpectedErrorException e){
            error = new Error(e.getMessage());
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(e).build());
        }
        if (error != null)
          throw new WebApplicationException(Response.status(400).entity(error).build());
        //return Response.ok(gson.toJson(sentMessage)).build();
        return response;
    }

}
