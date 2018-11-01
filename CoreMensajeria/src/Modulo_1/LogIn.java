package Modulo_1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Classes.Company;
import Classes.Sql;
import Classes.User;
import com.google.gson.Gson;

@Path("/users")
public class LogIn {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    public String GetUsers() throws SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        //String select = "SELECT * FROM user";

        ArrayList userList = new ArrayList();
        userList.add(new User(1,"Marco",2,"marcolbx@hotmail.com","04241983536",12-12-2020,"Venezuela","Caracas","Masculino","caracas"));

        try {
            ArrayList<User> userList= new ArrayList<>();
            Statement st = conn.createStatement();
            /*
            ResultSet result =  st.executeQuery(select);

            while(result.next()){
                User user = new User();
                user.set_usernameUser(result.getString("nameUser"));
                UserList.add(user);
            } */

            return gson.toJson(userList);
        }
        catch (SQLException e) {
            e.printStackTrace();
          //  throw new SQLException(select);
        }
        finally {
            Sql.bdClose(conn);
        }
    }

    // The Java method will process HTTP GET requests
    @Path("/login")
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String login(@QueryParam("username") String username,@QueryParam("password") String password) {
        // Return some cliched textual content
        return "Has intentado ingresar";
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