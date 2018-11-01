package webService.M01_Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Classes.Sql;
import Classes.User;
import com.google.gson.Gson;

@Path("/")
public class LogIn {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    public String GetUsers() throws SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM user";


        try {
            ArrayList<User> userList = new ArrayList<>();
            Statement st = conn.createStatement();

            ResultSet result =  st.executeQuery(select);

            while(result.next()){
                User user = new User();
                user.set_idUser(result.getInt("userId"));
                user.set_passwordUser(result.getString("userPassword"));
                user.set_usernameUser(result.getString("userUsername"));
                user.set_typeUser(result.getInt("userType"));
                user.set_emailUser(result.getString("userEmail"));
                user.set_phoneUser(result.getString("userPhone"));
                user.set_countryUser(result.getString("userCountry"));
                user.set_cityUser(result.getString("userCity"));
                user.set_addressUser(result.getString("userAddress"));
                user.set_dateOfBirthUser(result.getDate("userDayOfBirth"));
                user.set_genderUser(result.getString("userGenre"));
                userList.add(user);
            }

            return gson.toJson(userList);
        } catch (SQLException e) {
            e.printStackTrace();
              throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }


    // The Java method will process HTTP GET requests
    @Path("/login")
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@QueryParam("username") String username,@QueryParam("password") String password) {
        return null;
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