package webService.M01_Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

import Classes.LoginIntent;
import Classes.Sql;
import Classes.User;
import com.google.gson.Gson;

@Path("/")
public class M01_Login {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();
    User user = new User();
    ResultSet result = null;
    static String QUERY_SELECT = "SELECT * FROM public.user where use_username=?";



    // The Java method will process HTTP GET requests
    @Path("/login")
    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login( LoginIntent loginIntent) throws SQLException {
        Error error = null;


        try {
            if(loginIntent.get_username().matches("[a-zA-Z0-9]+") && loginIntent.get_password().matches("[a-zA-Z0-9/*_-]+")){
                PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SELECT);
                preparedStatement.setString(1, loginIntent.get_username());
                result = preparedStatement.executeQuery();
                user = new User();

                while (result.next()) {

                    user.set_idUser(result.getInt("use_Id"));
                    user.set_passwordUser(result.getString("use_password"));
                    user.set_usernameUser(result.getString("use_username"));
                    user.set_typeUser(result.getInt("use_type"));
                    user.set_emailUser(result.getString("use_email"));
                    user.set_phoneUser(result.getString("use_phone"));
                    user.set_countryUser(result.getString("use_country"));
                    user.set_cityUser(result.getString("use_city"));
                    user.set_addressUser(result.getString("use_address"));
                    user.set_dateOfBirthUser(result.getDate("use_date_of_birth"));
                    user.set_genderUser(result.getString("use_gender"));

                }
                if (user.get_passwordUser().equals(loginIntent.get_password())) {
                    user.set_passwordUser("");
                    return Response.accepted(gson.toJson(user)).build();
                } else {
                    error = new Error("Las credenciales ingresadas son incorrectas");
                    error.addError("credenciales","No se encontro el usuario deseado");
                    return Response.status(404).entity(error).build();
                }

            }
            else {
                error = new Error("Los datos ingresados no tienen el formato adecuado");
                error.addError("credenciales","Los valores no pueden incluir caracteres especiales que no sean: /*_-");
                return Response.status(404).entity(error).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            error.addError("Query deseado",QUERY_SELECT);
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
        } finally {
            Sql.bdClose(conn);
        }

    }

    @Path("/register")
    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON)
    public String register() {
        // Return some cliched textual content
        return "Has intentado registrarte";
    }
}