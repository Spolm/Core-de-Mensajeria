package webService.M06_DataOrigin;

import Classes.M06_DataOrigin.Application;
import Classes.M06_DataOrigin.ApplicationDAO;
import Classes.Sql;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@Path("/applications")
public class M06_Application {
    final String QUERY_INSERT_APPLICATION = "INSERT INTO public.application " +
            "(app_name,app_description,app_token,app_status,app_user_creator,app_date) values" +
            "(?, ?, ? , ?, ?, timestamp);";

    final String QUERY_DELETE_APPLICATION = "DELETE FROM public.application where use_id=?";

    Gson gson = new Gson();

    ApplicationDAO _applicationDAO = new ApplicationDAO();

    private Connection _conn = Sql.getConInstance();

    //private ResultSet _generatedKeys;


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetApplications() throws SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM public.application";

        try {
            ArrayList<Application> applicationList = new ArrayList<>();
            Statement st = _conn.createStatement();

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
            Sql.bdClose(_conn);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteApplication(@PathParam("id") int id) {

        try {
            _applicationDAO.deleteApplication(id);
            return Response.ok().build();
        } catch (SQLException e) {
            System.out.println("Error al ingresar id");
            return Response.status(404).build();

        }
    }
    /*    POR AHI VAN LOS TIROS
     @POST
     @Path("/")
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public Response addApplication(String nameApplication, String descriptionApplication){
        try{
            _applicationDAO.addApplication(nameApplication, descriptionApplication);
            return Response.ok().build();
        }catch (SQLException e){
            System.out.println("Error al ingresar nombre o descripcion de la aplicacion");
            return Response.status(404).build();
        }
     }*/


    /*      NI DE VAINA
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addApp(String nombre, String desc) throws SQLException {

        System.out.println("name: "+nombre);
        System.out.println("descriopcion: "+desc);
        try {
            //_applicationDAO.addApplication(application);
            PreparedStatement preparedStatement = _conn.prepareStatement(QUERY_INSERT_APPLICATION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,application.get_nameApplication());
            preparedStatement.setString(2,application.get_descriptionApplication());
            preparedStatement.setString(3,application.get_tokenApplication());
            preparedStatement.setInt(4,application.get_statusApplication());
            preparedStatement.setInt(5,application.get_userCreatorApplication());
            preparedStatement.execute();
            _generatedKeys= preparedStatement.getGeneratedKeys();
            if (_generatedKeys.next()) {
                application.set_idApplication(_generatedKeys.getInt(1));
            }

            return Response.ok(gson.toJson(application)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }finally {
            Sql.bdClose(_conn);
        }
    }*/


}