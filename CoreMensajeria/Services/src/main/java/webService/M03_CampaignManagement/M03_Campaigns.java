package webService.M03_CampaignManagement;

import Classes.M03_Campaign.Campaign;
import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;
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

@Path( "/M03_Campaign" )
public class M03_Campaigns {

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
        @Path("/GetCompanies")
        @Produces("application/json")


        public Response getCampaigns(@QueryParam("id") int id) throws CampaignDoesntExistsException {
            Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
            //Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
            String select = "SELECT * FROM campaign where cam_company_id = ?";
            ArrayList<Campaign> campaignList= new ArrayList<>();

            try {

                PreparedStatement ps = conn.prepareStatement(select);
                ps.setInt(1, id);
                ResultSet result = ps.executeQuery();
                //Statement st = conn.createStatement();
                //ResultSet result =  st.executeQuery(select);

                while(result.next()){
                    Campaign ca = new Campaign();
                    ca.set_idCampaign(result.getInt("cam_id"));
                    ca.set_nameCampaign(result.getString("cam_name"));
                    ca.set_descCampaign(result.getString("cam_description"));
                    ca.set_startCampaign(result.getDate("cam_start_date"));
                    ca.set_endCampaign(result.getDate("cam_end_date"));
                    ca.set_statusCampaign(result.getBoolean("cam_status"));
                    campaignList.add(ca);
                }
                rb.entity(gson.toJson(campaignList));
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new CampaignDoesntExistsException(e);
            }
            finally {
                Sql.bdClose(conn);
            }
            return rb.build();
        }

}
