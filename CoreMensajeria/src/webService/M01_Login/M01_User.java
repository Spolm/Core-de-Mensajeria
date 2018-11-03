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