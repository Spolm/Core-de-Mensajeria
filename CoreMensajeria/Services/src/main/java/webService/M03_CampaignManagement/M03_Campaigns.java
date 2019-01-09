package webService.M03_CampaignManagement;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdCompUser;
import DTO.M02_DTO.DTOIdCompany;
import DTO.M03_DTO.DTOFullCampaign;
import DTO.M03_DTO.DTOIdCampaign;
import DTO.M03_DTO.DTOIdStatusCampaign;
import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;

import Logic.Command;
import Logic.CommandsFactory;
import Logic.M03_Campaign.AddCampaignCommand;
import Logic.M03_Campaign.CampaignUserCommand;
import Logic.M03_Campaign.CampaignUserCompanyCommand;
import Logic.M03_Campaign.GetCampaignCommand;
import Mappers.CampaignMapper.MapperFullCampaign;
import Mappers.CampaignMapper.MapperIdCampaign;
import Mappers.CampaignMapper.MapperIdStatusCampaign;
import Mappers.CompanyMapper.MapperFullCompany;
import Mappers.CompanyMapper.MapperIdCompUser;
import Mappers.CompanyMapper.MapperIdCompany;
import Mappers.MapperFactory;

import com.google.gson.Gson;

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

   private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    private final String MESSAGE_ERROR_PARAMETERDOESNTEXIST= "La parametros ingresados no Validos";
    Gson gson = new Gson();
    ArrayList<Campaign> _caList = new ArrayList<>();





    @GET
    @Path("/CampaignDetails/{campaignId}")
    @Produces("application/json")
    public Response getCampaignDetails(  @PathParam("campaignId") int id) {
            Error _error;
            DTOIdCampaign _dto = DTOFactory.CreateDTOIdCampaign(id);
            Response.ResponseBuilder _rb = Response.status(Response.Status.ACCEPTED);
        try {
            MapperIdCampaign _map =  MapperFactory.createMapperIdCampaign();
            Campaign _ca =(Campaign) _map.CreateEntity( _dto );
            GetCampaignCommand _cmd = CommandsFactory.createGetCampaignCommand( _ca );
            _cmd.execute( );
            MapperFullCampaign _mapCamp = MapperFactory.CreateMapperFullCampaign();
            DTOFullCampaign  _campaing = _mapCamp.CreateDto( _cmd.Return() );
            _rb.entity( gson.toJson( _campaing ) );
        }

        catch (Exception e) {
            _error = new Error( MESSAGE_ERROR_INTERN );
            return Response.status(500).entity(_error).build();
        }
        return _rb.build();
    }

    @POST
    @Path("/AddCampaignP")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCampaign( DTOFullCampaign _dto ){
           Error _error;
           Response.ResponseBuilder _rb = Response.status(Response.Status.OK);
           Logger logger = Logger.getLogger(M03_Campaigns.class.getName());
           logger.info("Objeto compania recibido en AddCampaign" + _dto.get_idCampaign() + " " +
                     _dto.get_nameCampaign() + " "+ _dto.get_statusCampaign() + " " + _dto.get_descCampaign()+""+
                     _dto.get_startCampaign()+""+_dto.get_endCampaign()+"id:"+_dto.get_idCompany() );
        try {
           MapperFullCampaign _mapp = MapperFactory.CreateMapperFullCampaign();
            Campaign _ca = ( Campaign ) _mapp.CreateEntity( _dto );
           AddCampaignCommand _command = CommandsFactory.createAddCampaignCommand( _ca );
           _command.execute();
           return _rb.build() ;

        } catch (Exception e) {
            _error = new Error( MESSAGE_ERROR_INTERN );
            return Response.status(500).entity(_error).build();
        }
    }

    @GET
    @Path("/GetCampaignsByUser/{id}")
    @Produces("application/json")
    public Response getCampaignsByUser(@PathParam("id") int id)  {
            DTOIdCompany _dto = DTOFactory.CreateDTOIdCompany( id );
            Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperIdCompany _mapper = MapperFactory.createMapperIdCompany();
            Company _camp = (Company) _mapper.CreateEntity( _dto );
            CampaignUserCommand _command = CommandsFactory.createCampaignUserCommand( _camp );
            _command.execute();
            MapperFullCampaign _mappCamp = MapperFactory.CreateMapperFullCampaign();
            List< DTOFullCampaign > _dtoCa = _mappCamp.CreateDtoList( _command.ReturnList() ) ;
            _rb.entity( gson.toJson( _dtoCa ) ) ;
            return _rb.build();

         }catch (Exception e){
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }
     }

    @GET
    @Path("/GetCampaignsByCompany/{idCompany}/{idUser}")
    @Produces("application/json")
    public Response getCampaignsByCompanyUser(@PathParam ("idCompany") int _comp ,
                                              @PathParam("idUser") int _user){
            Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
            DTOIdCompUser _dto = DTOFactory.createDTOIdCompUser( _comp , _user );
        try {
            MapperIdCompUser _mapper = MapperFactory.createMapperIdCompUser();
            Company _camp = (Company) _mapper.CreateEntity( _dto );
            CampaignUserCompanyCommand _command = CommandsFactory.createCampaignUserCompany( _camp );
            _command.execute();
            MapperFullCampaign _caList = MapperFactory.CreateMapperFullCampaign();
            List< DTOFullCampaign > _dtoCa = _caList.CreateDtoList( _command.ReturnList() ) ;
            rb.entity(gson.toJson( _dtoCa ));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }
        return rb.build();
    }


    @PUT
    @Path("/Edit/Campaign")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editCampaign( DTOFullCampaign _dto ){
            Error _error;
            Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperFullCampaign _mapper = MapperFactory.CreateMapperFullCampaign();
            Entity _camp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createUpdateCampaignCommand( _camp );
            _command.execute();
            return _rb.build();
        } catch ( Exception e ){
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
            Campaign _comp = (Campaign)_mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createChangeStatusCampaign( _comp );
            _command.execute();
            return _rb.build();

        } catch (Exception e){
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }
    }
/*

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
*/

}
