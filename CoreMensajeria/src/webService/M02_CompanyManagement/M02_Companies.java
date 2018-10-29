package webService.M02_CompanyManagement;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.*;
//import com.google.gson.Gson;

import Classes.Company;
import Classes.Sql;



@Path("/M02_Companies")
public class M02_Companies {

    Connection conn = Sql.getConInstance();

    @POST
    @Path("/AddCompany")
    @Produces("application/json")

    public Response AddCompany(String name, String desc, boolean status) {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);

        try {
            Statement stmn = conn.createStatement();
            Company co = new Company(name, desc, status);
        }
        catch (SQLException e) {
            e.printStackTrace();;
        }
        return rb.build();
    }

}
