package webService.M07_Template;


import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Parameter;
import Exceptions.ParameterDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * M07_Parameter class is an API that is responsible for requesting information
 * about the parameters of a message
 */
@Path("/parameters")
@Produces(MediaType.APPLICATION_JSON)
public class M07_Parameter {

    /**
     * serialization and deserialization between Java objects
     */
    public Gson gson = new Gson();

    /**
     *this method is responsible for making a new parameter associated
     * with a company stored.
     * @param name name of the parameter
     * @param companyId company id
     */
    @POST
    @Path("add")
    public void postParameter(@FormParam("name") String name,@FormParam("companyId") int companyId){
        ParameterHandler parameterHandler = new ParameterHandler();
        parameterHandler.postParameter(name,companyId);
    }

    /**
     *this method returns all the parameters filtering by a specified company
     * @param companyId company id
     * @return ArrayList of the parameters by company
     */
    @GET
    @Path("get")
    public Response getParameters(@QueryParam("companyId") int companyId){
        ParameterHandler parameterHandler = new ParameterHandler();
        ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
        try {
            parameterList = parameterHandler.getParameters(companyId);
        }catch (ParameterDoesntExistsException e){
            //logg
        }
            return Response.ok(gson.toJson(parameterList)).build();
    }

}
