package webService.M06_DataOrigin;

import Classes.M06_DataOrigin.AddApplicationData;
import Classes.M06_DataOrigin.ApplicationDAO;
import com.google.gson.Gson;
import exceptions.AddApplicationProblemException;
import exceptions.ApplicationNotFoundException;
import exceptions.DatabaseConnectionProblemException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Path("/applications")
public class M06_Application {

    private Gson gson = new Gson();
    private ApplicationDAO _applicationDAO = new ApplicationDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplications())).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(gson.toJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(gson.toJson(e.getMessage())).build();
        }

    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(@PathParam("id") int id){
        try{
            System.out.println("Id API:"+id);
            return Response.ok(gson.toJson(_applicationDAO.getApplication(id))).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(gson.toJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(gson.toJson(e.getMessage())).build();
        }

    }

    @GET
    @Path("/token/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(@PathParam("token") String token){
        try{
            return Response.ok(gson.toJson(_applicationDAO.getApplication(token))).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(gson.toJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(gson.toJson(e.getMessage())).build();
        }

    }

    @PUT
    @Path("/active/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeApplication(@PathParam("id") int id) {

        try {
            String message = "Aplicacion actualizada.";
            _applicationDAO.updateApplication(id,1);
            return Response.ok(gson.toJson(message)).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(gson.toJson(e.getMessage())).build();
        }
    }
    @PUT
    @Path("/inactive/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inactiveApplication(@PathParam("id") int id) {

        try {
            String message = "Aplicacion actualizada.";
            _applicationDAO.updateApplication(id, 0);
            return Response.ok(gson.toJson(message)).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(gson.toJson(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteApplication(@PathParam("id") int id) {
        String message = "Aplicacion eliminada.";
        try {
            _applicationDAO.deleteApplication(id);
            return Response.ok(gson.toJson(message)).build();
        } catch (SQLException e) {
            return Response.status(404).build();
        }
    }


    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addApplication(AddApplicationData application){

        try {
            _applicationDAO.addApplication(application);
            return Response.ok().build();
        } catch (AddApplicationProblemException e) {
            return Response.status(404).entity(gson.toJson(e.getMessage())).build();
        }
    }
    /*    POR AHI VAN LOS TIROS
     @POST
     @Path("/")
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public Response addApplication(String nameApplication, String descriptionApplication){
        try{
            _applicationDAO.addApplication(nameApplication, descriptionApplication);
            return Response.ok().build();
        }catch (SQLException e){
            System.out.println("Error al ingresar nombre o descripcion de la aplicacion");
            return Response.status(404).build();
        }
     }*/


    /*      NI DE VAINA
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addApp(String nombre, String desc) throws SQLException {

        System.out.println("name: "+nombre);
        System.out.println("descriopcion: "+desc);
        try {
            //_applicationDAO.addApplication(application);
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT_APPLICATION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,application.get_nameApplication());
            preparedStatement.setString(2,application.get_descriptionApplication());
            preparedStatement.setString(3,application.get_tokenApplication());
            preparedStatement.setInt(4,application.get_statusApplication());
            preparedStatement.setInt(5,application.get_userCreatorApplication());
            preparedStatement.execute();
            _generatedKeys= preparedStatement.getGeneratedKeys();
            if (_generatedKeys.next()) {
                application.set_idApplication(_generatedKeys.getInt(1));
            }

            return Response.ok(gson.toJson(application)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            Sql.bdClose(_conn);
        }
    }*/


}