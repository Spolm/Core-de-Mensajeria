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
    static String QUERY_SELECT = "SELECT * FROM public.user where userUsername=?";



    // The Java method will process HTTP GET requests
    @Path("/login")
    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login( LoginIntent loginIntent) throws SQLException {
        Error error = null;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SELECT);
            preparedStatement.setString(1, loginIntent.get_username());
            result = preparedStatement.executeQuery();
            user = new User();

            while (result.next()) {

                user.set_idUser(result.getInt("userId"));
                user.set_passwordUser(result.getString("userPassword"));
                user.set_usernameUser(result.getString("userUsername"));
                user.set_typeUser(result.getInt("userType"));
                user.set_emailUser(result.getString("userEmail"));
                user.set_phoneUser(result.getString("userPhone"));
                user.set_countryUser(result.getString("userCountry"));
                user.set_cityUser(result.getString("userCity"));
                user.set_addressUser(result.getString("userAddress"));
                user.set_dateOfBirthUser(result.getDate("userDateOfBirth"));
                user.set_genderUser(result.getString("userGender"));

            }
            if (user.get_passwordUser().equals(loginIntent.get_password())) {
                user.set_passwordUser("");
                return Response.accepted(gson.toJson(user)).build();
            } else {
                error = new Error("No se pudo acceder al sistema");
                error.addError("credenciales","Las credenciales ingresadas son incorrectas");
                return Response.status(404).entity(error).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = new Error("Error a nivel de base de datos");
            error.addError("Query deseado","QUERY_SELECT");
            return Response.status(500).entity(error).build();
        } catch (NullPointerException e){
            error = new Error("No se pudo acceder al sistema");
            error.addError("credenciales","Las credenciales ingresadas son incorrectas");
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