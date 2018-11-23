package webService.M03_CampaignManagement;

import Classes.M03_Campaign.Campaign;
import Classes.Sql;
import Exceptions.CampaignDoesntExistsException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Path( "/M03_Campaigns" )
/**
 * Clase que se encarga de la logica del modulo campañas en el servicio REST
 */
public class M03_Campaigns {

    Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();

    //region Agregar Campaña
    /*
    @POST
    @Path( "/AddCampaign" )
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


    //region API detalle Campaña

    public Campaign getDetails (int id) throws CampaignDoesntExistsException {

        String select = "SELECT * FROM campaign where cam_id = ?";
        Campaign ca = new Campaign();
        try {

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ca.set_idCampaign(result.getInt("cam_id"));
                ca.set_nameCampaign(result.getString("cam_name"));
                ca.set_descCampaign(result.getString("cam_description"));
                ca.set_startCampaign(result.getDate("cam_start_date"));
                ca.set_endCampaign(result.getDate("cam_end_date"));
                ca.set_statusCampaign(result.getBoolean("cam_status"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ca;
    }

    //endregion

    //region API Obtener Campañas

    public ArrayList<Campaign> campaignList(int id) throws CampaignDoesntExistsException {
        String select = "SELECT * FROM campaign where cam_company_id = ?";
        ArrayList<Campaign> caList= new ArrayList<>();

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
                caList.add(ca);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return caList;
    }

    //endregion

    //region Detalle Campaña
    /**
     *
     * @param id Id de la campaña de la que se desean saber los detalles
     * @return
     * @throws CampaignDoesntExistsException exception personalizada que indica si la campaña no existe
     * en la base de datos
     */
    @GET
    @Path("/CampaignDetails")
    @Produces("application/json")

    public Response getCampaignDetails(@QueryParam("id") int id) {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        try {
            Campaign ca = getDetails(id);
            rb.entity(gson.toJson(ca));
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }
    //endregion



    //region Obtener Campañas
    @GET
    @Path("/GetCampaigns")
    @Produces("application/json")

    public Response getCampaigns(@QueryParam("id") int id) throws CampaignDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);

        try {
            ArrayList<Campaign> campaignList = campaignList(id);
            rb.entity(gson.toJson(campaignList));
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Sql.bdClose(conn);
        }
        return rb.build();
    }
    //endregion

    //Work in Progress
    //TODO fix this one
    //region Cambiar Status Campaña
    @POST
    @Path("/update/{campaignId}")
    public Response changeCampaignStatus(@PathParam("campaignId") int id){
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        Boolean flag = false;
        Campaign ca = new Campaign();
        ca.set_statusCampaign(!ca.is_statusCampaign());
        String query = "UPDATE public.campaign SET" +
                "cam_status ="+ ca.is_statusCampaign()+
                " WHERE cam_id ="+id;
        flag = ca.is_statusCampaign();
        rb.entity(gson.toJson(flag));
        return rb.build();
    }

    //endregion
    //TODO
    //region Modificar Campaña
    @PUT
    @Path("/EditCampaign")
    @Produces("application/json")
    public Response editCampaign() throws  CampaignDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String select = "SELECT * FROM campaign where cam_company_id = ?";
        ArrayList<Campaign> campaignList= new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(select);
            //ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            rb.entity(gson.toJson(campaignList));
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }


    //endregion
}
