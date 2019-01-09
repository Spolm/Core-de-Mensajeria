package webService.M08_MessageCoreManagement;


import DTO.M08_DTO.ParametersDTO;
import Exceptions.*;
import Logic.Command;
import Logic.CommandsFactory;
import Exceptions.M07_Template.TemplateDoesntExistsException;
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
        Exception error = null;
        ParametersDTO response = dto;
        try {
            Command<Boolean> c = CommandsFactory.createCommandValidate(dto);
            c.execute();
            if (c.Return() == true) {
                //Codigo cuando es exitoso

            }

        } catch (TemplateDoesntExistsException e) {
            error = e;
        } catch (SMSTooLongException e) {
            error = e;
        } catch (ParameterDoesntExistsException e) {
            error = e;
        } catch (MessageDoesntExistsException e) {
            error = e;
        } catch (TemplateNotApprovedException e) {
            error = e;
        } catch (UnexpectedErrorException e){
            error = e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(e).build());
        }
        if (error != null)
          throw new WebApplicationException(Response.status(400).entity(error).build());
        //return Response.ok(gson.toJson(sentMessage)).build();
        return response;
    }

}
