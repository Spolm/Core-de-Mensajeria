package webService.M01_Login;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.PrivilegeDao;
import Entities.M01_Login.User;
import Entities.M01_Login.UserDAO;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M01_Login.GetUserCommand;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/users")
public class M01_User {

    Gson _gson = new Gson();
    UserDAO _userDAO = new UserDAO();
    PrivilegeDao _privilegeDAO = new PrivilegeDao();

    /**
     * This method returns a response from the server when it gets
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUsers() {
        Error error;
        Entity user = EntityFactory.user();
        Command commandUser = CommandsFactory.instanciateGetUser(user);
        GetUserCommand cmd = (GetUserCommand) commandUser;
        //TODO acomodar esto
        Response rb;
        try {
             rb = Response.ok(_gson.toJson(_userDAO.findAll())).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e) {
            error = new Error("No se consiguio el usuario");
            error.addError("id", "No se encontro el usuario deseado");
            return Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion", e.getMessage());
            return Response.status(500).entity(error).build();
        }
        return rb;
    }

    /**
     * This method returns a response from the server when it gets the user or if it doesn't.
     * @param id
     * @return Response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUser(@PathParam("id") int id) {
        Error error;
        try {
            return Response.ok(_gson.toJson(_userDAO.findByUsernameId(id))).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e) {
            error = new Error("Las credenciales ingresadas son incorrectas");
            error.addError("credenciales", "No se encontro el usuario deseado");
            return Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion", e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user) {
        Error error;
        try {
            _userDAO.saveUser(user);
            return Response.ok(_gson.toJson(user)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion", e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }

    @GET
    @Path("/{id}/privileges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUserPrivileges(@PathParam("id") int id) {
        Error error;
        try {
            return Response.ok(_gson.toJson(_privilegeDAO.findPrivilegesByUserId(id))).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e) {
            error = new Error("Las credenciales ingresadas son incorrectas");
            error.addError("credenciales", "No se encontro el usuario deseado");
            return Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion", e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }
}