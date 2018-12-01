package webService.M01_Login;

import Classes.M01_Login.LoginIntent;
import Classes.M01_Login.User;
import Classes.M01_Login.UserDAO;
import Exceptions.UserBlockedException;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.SQLException;

@Path("/")
public class M01_Login {

    Gson _gson = new Gson();
    UserDAO _userDAO = new UserDAO();

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login( LoginIntent loginIntent){
        Error error;
        User user;
        try {
            if(loginIntent.get_username().matches("[a-zA-Z0-9.@+/*-]+") &&
                    loginIntent.get_password().matches("[a-zA-Z0-9/*_-]+")){

                if( _userDAO.isBlockedUser(loginIntent.get_username()) )
                    throw new UserBlockedException("El usuario ingresado se encuentra bloqueado");

                user = _userDAO.logUser(loginIntent.get_username(),loginIntent.get_password());
                if (user == null)
                    throw new NullPointerException();
                return Response.ok(_gson.toJson(user)).build();
            }
            else {
                error = new Error("Los datos ingresados no tienen el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                return Response.status(404).entity(error).build();
            }

        } catch(UserBlockedException e){
            e.printStackTrace();
            error = new Error("El usuario ha sido bloqueado");
            return Response.status(401).entity(error).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e){
            error = new Error("Las credenciales ingresadas son incorrectas");
            error.addError("credenciales","No se encontro el usuario deseado");
            return Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }
}