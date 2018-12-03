package webService.M02_CompanyManagement;

import Classes.M02_Company.CompanyDAO;
import Classes.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path( "/M02_Companies" )
public class M02_Companies {

    Gson gson = new Gson();
    ArrayList<Company> _coList = new ArrayList<>();
    Company _co = new Company();
    @GET
    @Path("/CompanyDetails")
    @Produces("application/json")

    public Response getCompanyDetails(@QueryParam("id") int id) throws CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CompanyDAO co = new CompanyDAO();
        try {
            _co = co.getDetails(id);
            rb.entity(gson.toJson(_co));
        }
        catch (CompanyDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rb.build();
    }

    @GET
    @Path("/GetCompanies")
    @Produces("application/json")


    public Response getCompanies(@QueryParam("id") int id) throws  CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CompanyDAO co = new CompanyDAO();
        try {
            _coList = co.companyList(id);
            rb.entity(gson.toJson(_coList));
        }
        catch (CompanyDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    @GET
    @Path("/GetAll")
    @Produces("application/json")


    public Response getAllCompanies() throws  CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CompanyDAO co = new CompanyDAO();
        try {
            _coList = co.companyListAll();
            rb.entity(gson.toJson(_coList));
        }
        catch (CompanyDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    //region Cambiar Status Compañia
    //TODO crear excepcion para este metodo
    @GET
    @Path("/update/{companyId}")
    //@Consumes("application/json")
    @Produces("text/plain")
    public Response changeCompanyStatus(@PathParam("companyId") int id) throws CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        Boolean flag;
        CompanyDAO co = new CompanyDAO();
        try {
            flag = co.changeStatus(id);
            rb.header("Company Status Changed", "Success");
            rb.tag("application/json");
            rb.entity(gson.toJson(flag));

        } catch (CompanyDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    //endregion

    //region Agregar/Editar Compañia

    /*@POST
    @Path("/EditCompany/{idCompany}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCompany (@FormParam("nameCompany") String name,@FormParam(descriptionCompany) String description,
                                 @FormParam("statusCompany") boolean status, @PathParam("idCompany") int id) {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String query = "UPDATE public.company SET com_name=?, "
            +"com_description=? com_status=? WHERE com_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,"com_name");
            ps.setString(2,"com_description");
            ps.setBoolean(3,"com_status");
            ps.setInt(4,"com_id");
            ps.executeUpdate();
            rb.header("Company Edited", "Success");
            rb.tag("application/json");
            rb.entity(gson.toJson(co));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }*/
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

    //endregion


}
