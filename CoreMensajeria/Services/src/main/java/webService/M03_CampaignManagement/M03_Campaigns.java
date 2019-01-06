package webService.M03_CampaignManagement;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdCompany;
import DTO.M03_DTO.DTOFullCampaign;
import DTO.M03_DTO.DTOIdCampaign;
import DTO.M03_DTO.DTOIdStatusCampaign;
import Entities.Entity;
import Entities.M03_Campaign.Campaign;
import Entities.M03_Campaign.CampaignDAO;
import Exceptions.CampaignDoesntExistsException;

import Logic.Command;
import Logic.CommandsFactory;
import Logic.M03_Campaign.AddCampaignCommand;
import Logic.M03_Campaign.CampaignUserCommand;
import Logic.M03_Campaign.GetCampaignCommand;
import Mappers.CampaignMapper.MapperFullCampaign;
import Mappers.CampaignMapper.MapperIdCampaign;
import Mappers.CampaignMapper.MapperIdStatusCampaign;
import Mappers.CompanyMapper.MapperFullCompany;
import Mappers.CompanyMapper.MapperIdCompany;
import Mappers.MapperFactory;
import Persistence.DAO;
import Persistence.M03_Campaign.DAOCampaign;
import com.google.gson.Gson;
import webService.M02_CompanyManagement.M02_Companies;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path( "/M03_Campaigns" )
/**
 * Clase que se encarga de la logica del modulo campañas en el servicio REST
 */
public class M03_Campaigns {

    Gson gson = new Gson();
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


/*
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
    }*/
/*
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
    }*/

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

/*
    //region Campañas por compañia y usuario
    @GET
    @Path("/GetCampaignsByCompany")
    @Produces("application/json")

    public Response getCampaignsByCompanyUser(@QueryParam("idCompany") int idCompany,
                                              @QueryParam("idUser") int idUser) throws CampaignDoesntExistsException {
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
    }*/

 /*   @POST
    @Path("/AddCampaign")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCampaign(Campaign _campaign){
        Response.ResponseBuilder rb = Response.status(Response.Status.OK);
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
    }*/

   /* @PUT
    @Path("/Edit/Camaign/{campaignId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response UpdateCampaign(Campaign _campaign,@PathParam("campaignId") int id){
            Response.ResponseBuilder rb = Response.status(Response.Status.OK);
            CampaignDAO _campaignDAO = new CampaignDAO();

        try {
            _campaignDAO.updateCompany(id,_campaign);
        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }*/


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
    @Path("/CampaignDetails/{campaignId}")
    @Produces("application/json")
    public Response getCampaignDetails(  @PathParam("campaignId") int id) {
            DTOIdCampaign _dto = DTOFactory.CreateDTOIdCampaign(id);
            Response.ResponseBuilder _rb = Response.status(Response.Status.ACCEPTED);
        try {
            MapperIdCampaign _map =  MapperFactory.createMapperIdCampaign();
            Entity _ca = _map.CreateEntity( _dto );
            GetCampaignCommand _cmd = CommandsFactory.createGetCampaignCommand( _ca );
            _cmd.execute( );
            MapperFullCampaign _mapCamp = MapperFactory.CreateMapperFullCampaign();
            DTOFullCampaign  _campaing = _mapCamp.CreateDto( _cmd.Return() );
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

    @POST
    @Path("/AddCampaignP")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCampaign( DTOFullCampaign _dto ){
           Response.ResponseBuilder _rb = Response.status(Response.Status.OK);
           Logger logger = Logger.getLogger(M02_Companies.class.getName());
           logger.info("Objeto compania recibido en AddCampaign" + _dto.get_idCampaign() + " " +
                     _dto.get_nameCampaign() + " "+ _dto.get_statusCampaign() + " " + _dto.get_descCampaign()+""+
                     _dto.get_startCampaign()+""+_dto.get_endCampaign()+"id:"+_dto.get_idCompany() );
        try {
           MapperFullCampaign _mapp = MapperFactory.CreateMapperFullCampaign();
           Entity _ca = _mapp.CreateEntity( _dto );
           AddCampaignCommand _command = CommandsFactory.createAddCampaignCommand( _ca );
           _command.execute();
           return _rb.build() ;

        } catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return _rb.build();
    }

    @GET
    @Path("/GetCampaignsByUser/{id}")
    @Produces("application/json")
    public Response getCampaignsByUser(@QueryParam("id") int id)  {
            DTOIdCompany _dto = DTOFactory.CreateDTOIdCompany(id);
            Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperIdCompany _mapper = MapperFactory.createMapperIdCompany();
            Entity _comp = _mapper.CreateEntity( _dto );
            CampaignUserCommand _command = CommandsFactory.createCampaignUserCommand( _comp );
            _command.execute();
            MapperFullCampaign _mappCamp = MapperFactory.CreateMapperFullCampaign();
            List< DTOFullCampaign > _dtoCo = _mappCamp.CreateDtoList( _command.ReturnList() ) ;
            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();

        }
        catch (Exception e){


            return Response.status( 500 ).entity( e.getMessage() ).build();
        }
     }

  /*  @GET
    @Path("/GetCampaignsByCompany")
    @Produces("application/json")

    public Response getCampaignsByCompanyUser() throws CampaignDoesntExistsException {
            Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);

        try {

            rb.entity(gson.toJson(_caList));
        }
        catch (CampaignDoesntExistsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    } */


    @PUT
    @Path("/Edit/Campaign")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editCampaign( DTOFullCampaign _dto ){

            Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperFullCampaign _mapper = MapperFactory.CreateMapperFullCampaign();
            Entity _camp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createUpdateCampaignCommand( _camp );
            _command.execute();
            return _rb.build();
        }
        catch ( Exception e ){

            return Response.status( 500 ).entity( e.getMessage() ).build();
        }


    }

    @POST
    @Path("/updateCampaignStatus")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response changeCampaignStatus( DTOIdStatusCampaign _dto ){

        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperIdStatusCampaign _mapper =  MapperFactory.createMapperIdStatusCampaign();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createChangeStatusCampaign( _comp );
            _command.execute();
            return _rb.build();

        }
        catch (Exception e){
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }

    }

}
