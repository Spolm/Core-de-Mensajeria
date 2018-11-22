package webService.M02_CompanyManagement;

import Classes.M02_Company.Company;
import Classes.Sql;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Path( "/M02_Companies" )
public class M02_Companies {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();
/*
    @POST
    @Path( "/AddCompany" )
    @Produces( "application/json" )

    public String addCompany( @FormParam( "name" ) String name, @FormParam( "description" ) String description,
                             @FormParam( "status" ) boolean status, @FormParam( "user" ) int user ) throws  SQLException
    {
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String query = "INSERT INTO company ( com_name, com_description, com_status, com_user_id ) VALUES "+name+","+description+","+status+","+user;


        try {
            PreparedStatement ps = conn.prepareStatement(query);
            User us = new User();
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBoolean(3, status);
            ps.executeUpdate();
            Company co = new Company(name, description, status);
            return gson.toJson(co);


        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(query);
        }
        finally {
            Sql.bdClose(conn);
        }
    }*/


    @GET
    @Path("/CompanyDetails")
    @Produces("application/json")

    public Response getCompanyDetails(@QueryParam("id") int id) throws CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM company where com_id = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            Company co = new Company();
            while (result.next()) {
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));
            }
            rb.entity(gson.toJson(co));
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rb.build();
    }



    @GET
    @Path("/GetCompanies")
    @Produces("application/json")


    public Response getCompanies(@QueryParam("id") int id) throws  SQLException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM company where com_user_id = ?";
        ArrayList<Company> companyList= new ArrayList<>();

        try {

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            //Statement st = conn.createStatement();
            //ResultSet result =  st.executeQuery(select);

            while(result.next()){
                Company co = new Company();
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));
                companyList.add(co);
            }
            rb.entity(gson.toJson(companyList));
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(select);
        }
        finally {
            Sql.bdClose(conn);
        }
        return rb.build();
    }
}
