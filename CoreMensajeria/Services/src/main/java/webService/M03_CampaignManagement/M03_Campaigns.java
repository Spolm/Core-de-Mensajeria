package webService.M03_CampaignManagement;

import DTO.M03_DTO.DTOFullCampaign;
import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Entities.M03_Campaign.CampaignDAO;
import Exceptions.CampaignDoesntExistsException;
import Factory.DTOFactory;
import Factory.MapperFactory;
import Logic.Command;
import Logic.CommandsFactory;
import Mappers.CampaignMapper.MapperFullCampaign;
import Mappers.CompanyMapper.MapperFullCompany;
import Persistence.DAO;
import Persistence.M03_Campaign.DAOCampaign;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path( "/M03_Campaigns" )
/**
 * Clase que se encarga de la logica del modulo campañas en el servicio REST
 */
public class M03_Campaigns {

    Gson gson = new Gson();
    Campaign _ca = new Campaign();
    ArrayList<Campaign> _caList = new ArrayList<>();


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

    //region Detalle Campaña

    /**
     *
     * @param id recibe el id de la campaña que se desea ver con detalle
     * @return Response Builder con los detalles de la campaña
     */

  /*
    @GET
    @Path("/CampaignDetails")
    @Produces("application/json")

    public Response getCampaignDetails(@QueryParam("id") int id) {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CampaignDAO ca = new CampaignDAO();
        try {
            _ca = ca.getDetails(id);
            rb.entity(gson.toJson(_ca));
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }    */
    //endregion



    //Work in Progress
    //TODO fix this one
    //region Cambiar Status Campaña
    @GET
    @Path("/update/{campaignId}")
    //@Consumes("application/json")
    @Produces("text/plain")
    public Response changeCampaignStatus(@PathParam("campaignId") int id) {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        Boolean flag;
        CampaignDAO ca = new CampaignDAO();
        try {
            flag = ca.changeStatusCampaign(id);
            rb.header("Campaign Edited", "Success");
            rb.tag("application/json");
            rb.entity(gson.toJson(flag));

        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    //region metodo campaigns by user
    @GET
    @Path("/GetCampaignsByUser")
    @Produces("application/json")

    public Response getCampaignsByUser(@QueryParam("id") int id) throws CampaignDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CampaignDAO caList = new CampaignDAO();
        try {
            _caList = caList.campaignListByUser(id);
            rb.entity(gson.toJson(_caList));
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
        CampaignDAO caList = new CampaignDAO();
        try {
            _caList = caList.campaignList(id);
            rb.entity(gson.toJson(_caList));
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

    //region Campañas por compañia y usuario
    @GET
    @Path("/GetCampaignsByCompany")
    @Produces("application/json")

    public Response getCampaignsByCompanyUser(@QueryParam("idCompany") int idCompany, @QueryParam("idUser") int idUser) throws CampaignDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CampaignDAO caList = new CampaignDAO();
        try {
            _caList = caList.campaignListByUserCompany(idCompany,idUser);
            rb.entity(gson.toJson(_caList));
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    @POST
    @Path("/AddCampaign")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCampaign(Campaign _campaign){
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CampaignDAO _campaignDAO = new CampaignDAO();

        try {
            _campaignDAO.createCampaign(_campaign);
        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }

    //endregion

    //endregion

    //region Modificar Campaña
    /*
    @PUT
    @Path("/EditCampaign")
    @Produces("application/json")
    public Response editCampaign(@FormParam("campaignName") String campaignName,
                                 @FormParam("campaignDescription") String campaignDescription,
                                 @FormParam("campaignStart") Date campaignStart,
                                 @FormParam("campaignEnd") Date campaignEnd,
                                 @FormParam("campaignStatus") boolean campaignStatus, @QueryParam("campaignId") int id)
            throws  CampaignDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        String query = "UPDATE public.campaign SET cam_name =?, cam_description=?, cam_start_date=?, " +
                "cam_end_date=?, cam_status=? WHERE cam_company_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, campaignName);
            ps.setString(2, campaignDescription);
            ps.setDate(3, (java.sql.Date) campaignStart);
            ps.setDate(4, (java.sql.Date) campaignEnd);
            ps.setBoolean(5, campaignStatus);
            ps.setInt(6, id);
            ResultSet result = ps.executeQuery();

            rb.entity(gson.toJson(result));
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CampaignDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }*/


    //endregion

  ////////////////////////////////PATRONES///////////////////


    @GET
    @Path("/CampaignDetails")
    @Produces("application/json")
    public Response getCampaignDetails( DTOFullCampaign _dto ) {
        Response.ResponseBuilder _rb = Response.status(Response.Status.ACCEPTED);
        try {
            MapperFullCampaign _map =  MapperFactory.CreateMapperFullCampaign();
            Entity _ca = _map.CreateEntity( _dto );
            Command _cmd = CommandsFactory.createGetCampaignCommand( _ca );
            _cmd.execute( );
           DTOFullCampaign  _campaing = _map.CreateDto( _cmd.Return() );
            _rb.entity( gson.toJson( _campaing ) );
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return _rb.build();
    }





}
