package webService.M06_DataOrigin;

import Classes.M06_DataOrigin.AddApplicationData;
import Classes.M06_DataOrigin.ApplicationDAO;
import com.google.gson.*;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/applications")
public class M06_Application {

    private Gson gson = new Gson();
    private ApplicationDAO _applicationDAO = new ApplicationDAO();

    //                   GET ENDPOINTS
    //Get all applications endpoint. Path: applications
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplications())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }

    }

    //Get all applications by company ID endpoint. Path: applications/company/{companyID}
    @GET
    @Path("/company/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(@PathParam("companyId") int companyId){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplications(companyId))).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }

    }

    //Get application by application ID endpoint. Path: applications/id/(id)
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplication(@PathParam("id") int id){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplication(id))).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }

    }

    //Get application by Token endpoint. Path: applications/token/{token}
    @GET
    @Path("/token/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplication(@PathParam("token") String token){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplication(token))).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }

    }

    //                   PUT ENDPOINTS
    //Activate an application by application Id. Path: applications/active/{id}
    @PUT
    @Path("/active/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeApplication(@PathParam("id") int id) {
        try {
            _applicationDAO.updateApplication(id,1);
            return Response.ok(generateSuccessAsJson("Aplicacion activada exitosamente.")).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
    }

    //Inactivate an application by application Id. Path: applications/inactive/{id}
    @PUT
    @Path("/inactive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inactiveApplication(@PathParam("id") int id) {
        try {
            _applicationDAO.updateApplication(id, 0);
            return Response.ok(this.generateSuccessAsJson("Aplicacion pausada exitosamente.")).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
    }

    //                   DELETE ENDPOINTS
    //Delete an application by application Id. Path: applications/{id}
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteApplication(@PathParam("id") int id) {
        try {
            _applicationDAO.deleteApplication(id);
            return Response.ok(this.generateSuccessAsJson("Aplicacion eliminada exitosamente.")).build();
        } catch (ApplicationNotFoundException e) {
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
    }

    //                   POST ENDPOINTS
    //Add a new application. Path: applications/
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addApplication(AddApplicationData application){
        try {
            _applicationDAO.addApplication(application);
            return Response.ok(this.generateSuccessAsJson("Aplicacion creada exitosamente.")).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
    }

    //                   UTILITIES
    //Produces a Json String with a given success message
    private String generateSuccessAsJson(String message){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("_message", message);
        return gson.toJson(jsonResponse);
    }

    //Produces a Json String with a given error message
    private String generateErrorAsJson(String message){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("_message", message);
        return gson.toJson(jsonResponse);
    }
}