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
                if(user.get_blockedUser()==1)throw new UserBlockedException("El usuario ingresado se encuentra bloqueado");
                if (user.get_passwordUser().equals(loginIntent.get_password()) && user.get_blockedUser()==0) {
                    user.set_remainingAttemptsUser(3);
                    _userDAO.updateUserRemainingAttempts(user);
                    user.set_passwordUser("");

                    return Response.ok(_gson.toJson(user)).build();
                } else {
                    error = new Error("Las credenciales ingresadas son incorrectas");
                    if(user.get_remainingAttemptsUser()>0 && user.get_blockedUser()== 0) {
                        user.set_remainingAttemptsUser(user.get_remainingAttemptsUser() - 1);
                        _userDAO.updateUserRemainingAttempts(user);}
                        else if (user.get_blockedUser() == 1){
                        user.set_blockedUser(1);
                        _userDAO.blockUser(user);
                    }
                    error.addError("credenciales","No se encontro el usuario deseado o la clave es errada");
                    return Response.status(404).entity(error).build();
                }
            }
            else {
                error = new Error("Los datos ingresados no tienen el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                return Response.status(404).entity(error).build();
            }

        }
        catch(UserBlockedException e){
            e.printStackTrace();
            error = new Error("El usuario ha sido bloqueado");
            return Response.status(401).entity(error).build();
        }
        catch (SQLException e) {
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