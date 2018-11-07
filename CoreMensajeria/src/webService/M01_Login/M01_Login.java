package webService.M01_Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Classes.M01_Login.LoginIntent;
import Classes.M01_Login.UserDAO;
import Classes.M01_Login.User;
import com.google.gson.Gson;

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

                user = _userDAO.findByUsernameOrEmail(loginIntent.get_username());

                if (user.get_passwordUser().equals(loginIntent.get_password())) {
                    user.set_passwordUser("");
                    return Response.accepted(_gson.toJson(user)).build();
                } else {
                    error = new Error("Las credenciales ingresadas son incorrectas");
                    error.addError("credenciales","No se encontro el usuario deseado");
                    return Response.status(404).entity(error).build();
                }
            }
            else {
                error = new Error("Los datos ingresados no tienen el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                return Response.status(404).entity(error).build();
            }

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