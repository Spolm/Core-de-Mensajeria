package webService.M01_Login;

import Classes.Sql;
import Classes.User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

@Path("/users")
public class M01_User {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUsers() throws SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM public.user";


        try {
            ArrayList<User> userList = new ArrayList<>();
            Statement st = conn.createStatement();

            ResultSet result = st.executeQuery(select);

            while (result.next()) {
                User user = new User();
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
                userList.add(user);
            }
            return Response.ok(gson.toJson(userList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }
}