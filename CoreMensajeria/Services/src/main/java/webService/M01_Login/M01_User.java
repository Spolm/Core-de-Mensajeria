package webService.M01_Login;

import DTO.M01_DTO.DTOUser;
import Entities.Entity;
//import Entities.M01_Login.PrivilegeDao;
import Entities.M01_Login.User;
import Logic.M01_Login.FindByUsernameIdCommand;
import Logic.M01_Login.FindPrivilegeByUserIdCommand;
import Logic.M01_Login.SetPrivilegeParamsCommand;

import Logic.CommandsFactory;
import Logic.M01_Login.GetAllUsersCommand;
import Mappers.LoginMapper.UserMapper;

import Mappers.MapperFactory;
import Persistence.M01_Login.DAOUser;
import Persistence.M01_Login.DAOPrivilege;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class M01_User {

    Gson _gson = new Gson();
    DAOUser _userDAO = new DAOUser();
    /**
     * This method returns a response from the server when it gets
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUsers() {
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
                GetAllUsersCommand _command = CommandsFactory.createGetAllUsersCommand();
                _command.execute();
                UserMapper _mapper = MapperFactory.createUserMapper();
                ArrayList<Entity> _user = _command.ReturnList();
                List<DTOUser> _dtoUs = _mapper.CreateDtoList(_user);
                _rb.entity( _gson.toJson( _dtoUs ) ) ;
            }
         catch (SQLException e) {
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
        return _rb.build();
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
            FindByUsernameIdCommand _command = CommandsFactory.findByUsernameIdCommand(id);
            _command.execute();
            return Response.ok(_gson.toJson(_command.Return())).build();
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
            FindPrivilegeByUserIdCommand _command = CommandsFactory.findPrivilegeByUserIdCommand(id);
            _command.execute();
            return Response.ok(_gson.toJson(_command.Return())).build();
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