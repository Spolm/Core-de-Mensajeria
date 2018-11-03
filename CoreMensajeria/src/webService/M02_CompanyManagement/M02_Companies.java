package webService.M02_CompanyManagement;
import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;

import com.google.gson.Gson;

import Classes.Company;
import Classes.Sql;



@Path("/M02_Companies")
public class M02_Companies {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    @POST
    @Path("/AddCompany")
    @Produces("application/json")


    public String AddCompany(@QueryParam("name") String nameCompany, @QueryParam("descr") String descr, @QueryParam("status") boolean status) throws  SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String query = "INSERT INTO company (nameCompany, descr, status) VALUES "+nameCompany+","+descr+","+status;


        try {
            PreparedStatement ps = conn.prepareStatement(query);
            //Company co = new Company(name, desc, status);
            //ps.setString(1, nameCompany);
            //ps.setString(2, descr);
            // ps.setBoolean(3, status);
            ps.executeUpdate();
            Company co = new Company(nameCompany, descr, status);
            return gson.toJson(co);


        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(query);
        }
        finally {
            Sql.bdClose(conn);
        }
    }

    @GET
    @Path("/GetCompanies")
    @Produces("application/json")


    public String GetCompanies() throws  SQLException {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM company";
        ArrayList<Company> companyList= new ArrayList<>();

        try {

            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(select);

            while(result.next()){
                Company co = new Company();
                co.set_name(result.getString("nameCompany"));
                co.set_desc(result.getString("descr"));
                co.set_status(result.getBoolean("status"));
                companyList.add(co);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        }
        finally {
            Sql.bdClose(conn);
        }
        return gson.toJson(companyList);
    }
}
