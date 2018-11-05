package webService.M06_DataOrigin;

import Classes.Modulo_6.Application;
import Classes.Sql;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

@Path("/applications")
public class M06_Application {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetApplications() throws SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM public.application";

        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            Statement st = conn.createStatement();

            ResultSet result = st.executeQuery(select);

            while (result.next()) {
                Application application = new Application();
                application.set_idApplication(result.getInt("app_id"));
                application.set_nameApplication(result.getString("app_name"));
                application.set_descriptionApplication(result.getString("app_description"));
                application.set_tokenApplication(result.getString("app_token"));
                application.set_dateOfCreateApplication(result.getDate("app_date"));
                application.set_statusApplication(result.getInt("app_status"));
                application.set_userCreatorApplication(result.getInt("app_user_creator"));
                applicationList.add(application);
            }
            return Response.ok(gson.toJson(applicationList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        } finally {
            Sql.bdClose(conn);
        }
    }
}