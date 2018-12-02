package webService.M01_Login;

import Classes.M01_Login.*;
import Exceptions.UserBlockedException;
import com.google.gson.Gson;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.DatatypeConverter;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;


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

    @Context
    UriInfo uri;

    @GET
    @Path("/request_password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RequestPassword(@QueryParam("email") String email){
        Error error;
        try {

            if(email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")){
                String token = _userDAO.tokenGenerator(email);
                MailSender.generateAndSendEmail(token,email);
                return Response.ok(_gson.toJson("Revisa tu bandeja de entrada donde se envió el código de verificación")).build();
            }else {
                error = new Error("El Email ingresado no tiene el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                return Response.status(404).entity(error).build();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            error = new Error("El correo ingresado no se encontró");
            error.addError("credenciales","No se encontro el usuario deseado");
            return Response.status(404).entity(error).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            return Response.status(500).entity(error).build();
        } catch (Exception e){
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }

    @Path("/change_password")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login( PasswordChange passwordChange){
        Error error;
        User user;
        int x;
        try {
            user = _userDAO.findByUsernameOrEmail(passwordChange.get_username());
            if(_userDAO.tokenGenerator(user.get_emailUser()).equals(passwordChange.get_token()))
                _userDAO.changePassword(user.get_usernameUser(),passwordChange.get_newPassword());
            else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Response.ok(_gson.toJson("hola")).build();
    }
}