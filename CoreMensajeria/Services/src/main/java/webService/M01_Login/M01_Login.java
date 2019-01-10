package webService.M01_Login;

import DTO.M01_DTO.DTOLogin;
import Entities.Entity;
import Entities.M01_Login.*;
import Exceptions.UserBlockedException;
import Logic.CommandsFactory;
import Logic.M01_Login.*;
import Mappers.LoginMapper.LoginMapper;
import Mappers.MapperFactory;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;


@Path("/")
public class M01_Login {

    final static org.apache.logging.log4j.Logger log = LogManager.getLogger("CoreMensajeria");
    Gson _gson = new Gson();

    /**
     * This method is the connection between front-end and back-end. Verifies that the input data matches with the data
     * in the Data Base.
     * @param loginIntent
     * @return Response
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login( LoginIntent loginIntent) throws Exception {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo login()" );
        //endregion
        Error error;
        //LoginMapper _mapper = MapperFactory.createLoginMapper();
        LoginIntent _log; //= (LoginIntent) _mapper.CreateEntity(login);
        User user;
        LogUserCommand _command = CommandsFactory.createLogUserCommand(loginIntent);
        IsBlockedUserCommand _cmd = CommandsFactory.isBlockedUserCommand(loginIntent);
        //DTOLogin _dtoLog = _mapper.CreateDto(_log);
        try {

            if(loginIntent.get_username().matches("[a-zA-Z0-9.@+/*-]+") &&
                    loginIntent.get_password().matches("[a-zA-Z0-9/*_-]+")){
                _cmd.execute();
                if( _cmd.returnBool() )
                    throw new UserBlockedException("El usuario ingresado se encuentra bloqueado");
                _command.execute();
                user = (User) _command.Return();
                if (user == null)
                    throw new NullPointerException();
                //region Instrumentation Info
                log.info("Ejecutado el metodo login() con exito" );
                //endregion
                return Response.ok(_gson.toJson(user)).build();
            }
            else {
                error = new Error("Los datos ingresados no tienen el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                //region Instrumentation Error
                log.error("El metodo login() arrojo el error:" + error.get_error());
                //endregion
                return Response.status(404).entity(error).build();
            }

        } catch(UserBlockedException e){
            e.printStackTrace();
            error = new Error("El usuario ha sido bloqueado");
            //region Instrumentation Error
            log.error("El metodo login() arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(401).entity(error).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            //region Instrumentation Error
            log.error("El metodo login() arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e){
            error = new Error("Las credenciales ingresadas son incorrectas");
            error.addError("credenciales","No se encontro el usuario deseado");
            //region Instrumentation Error
            log.error("El metodo login() arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(404).entity(error).build();
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            //region Instrumentation Error
            log.error("El metodo login() arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(500).entity(error).build();
        }
    }

    @Context
    UriInfo uri;

    /**
     * This method is the connection between front-end and back-end. Allows an user to request a password, and it send and generates an email.
     * @param email
     * @return Response
     */
    @GET
    @Path("/request_password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestPassword(@QueryParam("email") String email){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo requestPassword("+email+")" );
        //endregion
        Error error;
        TokenGeneratorCommand _command = CommandsFactory.tokenGeneratorCommand(email);
        try {

            if(email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")){
                _command.execute();
                String token = _command.returnString();
                Logger logger = Logger.getLogger(getClass().getName());
                logger.info("token: "+token);
                MailSender.generateAndSendEmail(token,email);
                //region Instrumentation Info
                log.info("Ejecutado el metodo requestPassword("+email+") con exito" );
                //endregion
                return Response.ok(_gson.toJson("Revisa tu bandeja de entrada donde se envió el código de verificación")).build();
            }else {
                error = new Error("El Email ingresado no tiene el formato adecuado");
                error.addError("credenciales"
                        ,"Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                //region Instrumentation Error
                log.error("El metodo requestPassword("+email+") arrojo la excepcion:" + error.get_error());
                //endregion
                return Response.status(404).entity(error).build();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            error = new Error("El correo ingresado no se encontró");
            error.addError("credenciales","No se encontro el usuario deseado");
            //region Instrumentation Error
            log.error("El metodo requestPassword("+email+") arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(404).entity(error).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            //region Instrumentation Error
            log.error("El metodo requestPassword("+email+") arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(500).entity(error).build();
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            //region Instrumentation Error
            log.error("El metodo requestPassword("+email+") arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(500).entity(error).build();
        } catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo requestPassword("+email+") arrojo la excepcion:" + e.getMessage());
            //endregion
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }

    /**
     * This method is the connection between front-end and back-end. Allows the user to change his password.
     * @param passwordChange
     * @return Response
     */
    @Path("/change_password")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword( PasswordChange passwordChange){
        //region Instrumentation Debug
        log.debug("Entrando a el metodo changePassword("+passwordChange+")" );
        //endregion
        Error error;
        FindByUsernameOrEmailCommand _command =
                CommandsFactory.findByUsernameOrEmailCommand(passwordChange.get_username());

        User user;
        try {
            _command.execute();
            user = (User) _command.Return();//_userDAO.findByUsernameOrEmail(passwordChange.get_username());
            TokenGeneratorCommand _cmd = CommandsFactory.tokenGeneratorCommand(user.get_emailUser());
            _cmd.execute();
            if(_cmd.returnString().equals(passwordChange.get_token())){
                Logger logger = Logger.getLogger(getClass().getName());
                logger.info("Password: " + passwordChange.get_newPassword());

                if(passwordChange.get_newPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")){
                    ChangePasswordCommand passCmd = CommandsFactory.changePasswordCommand(user.get_usernameUser(), passwordChange.get_newPassword());
                    passCmd.execute();
                    //region Instrumentation Info
                    log.info("Ejecutado el metodo changePassword("+passwordChange+") con exito" );
                    //endregion
                    return Response.ok(_gson.toJson("Clave cambiada exitosamente")).build();
                } else{
                    error = new Error("La clave no es segura");
                    error.addError("clave"
                            ,"la clave ingressada no es válida");
                    //region Instrumentation Error
                    log.error("El metodo changePassword("+passwordChange+") arrojo el error:" + error.get_error());
                    //endregion
                    return Response.status(404).entity(error).build();
                }
            }
            else {
                error = new Error("El código ingresado no es válido");
                error.addError("Token","El token no es válido");
                //region Instrumentation Error
                log.error("El metodo changePassword("+passwordChange+") arrojo el error:" + error.get_error());
                //endregion
                return Response.status(404).entity(error).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            //region Instrumentation Error
            log.error("El metodo changePassword() arrojo la excepcion:" + e.getMessage());
            //endregion
            return Response.status(500).entity(error).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            error = new Error("Error Interno");
            //region Instrumentation Error
            log.error("El metodo changePassword("+passwordChange+") arrojo la excepcion:" + e.getMessage());
            //endregion
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        } catch (Exception e){
            e.printStackTrace();
            //region Instrumentation Error
            log.error("El metodo changePassword("+passwordChange+") arrojo la excepcion:" + e.getMessage());
            //endregion
            error = new Error("Error Interno");
            error.addError("Excepcion",e.getMessage());
            return Response.status(500).entity(error).build();
        }
    }
}