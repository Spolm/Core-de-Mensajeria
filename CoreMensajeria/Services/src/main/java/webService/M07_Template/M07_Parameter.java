package webService.M07_Template;


import DTO.M07_Template.NewParameter;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.ParameterDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;
import webService.M01_Login.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * M07_Parameter class is an API that is responsible for requesting information
 * about the parameters of a message
 */
@Path("/parameters")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Parameter {
    private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    private final String MESSAGE_ERROR_PARAMETERDOESNTEXIST= "El parámetro ingresado no existe";

    /**
     *this method is responsible for making a new parameter associated
     * with a company stored.
     * @param newParameter new parameter with its name and companyid
     */
    @POST
    @Path("add")
    public Response postParameter(NewParameter newParameter) {
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
            response = Response.ok(c.Return()).build();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
        }
        return response;
    }

    /**
     *this method returns all the parameters filtering by a specified company
     * @param companyId company id
     * @return ArrayList of the parameters by company
     */
    @GET
    @Path("get")
    public Response getParameters(@QueryParam("companyId") int companyId){
        Response response;
        Error error;
        try {
            if(companyId==0){
                throw new InvalidParameterException();
            }
            Command c = CommandsFactory.createCommandGetParameters(companyId);
            c.execute();
            response = Response.ok(c.Return()).build();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            error = new Error(e.getMessage());
            response = Response.status(404).entity(error).build();
        }catch (ParameterDoesntExistsException e){
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_PARAMETERDOESNTEXIST);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error(MESSAGE_ERROR_INTERN);
            error.addError(MESSAGE_EXCEPTION,e.getMessage());
            response = Response.status(500).entity(error).build();
        }
        return response;
    }

}
