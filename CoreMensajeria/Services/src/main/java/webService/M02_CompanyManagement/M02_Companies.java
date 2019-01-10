package webService.M02_CompanyManagement;

import DTO.DTOFactory;
import DTO.M02_DTO.*;
import Entities.Entity;
import Entities.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.ParameterCantBeNullException;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M02_Company.AddCompanyCommand;
import Logic.M02_Company.GetAllCompaniesCommand;
import Logic.M02_Company.GetCompanyByUserCommand;
import Logic.M02_Company.GetCompanyResponsibleCommand;
import Mappers.CompanyMapper.*;
import Mappers.MapperFactory;
import webService.M01_Login.Error;
import Persistence.M02_Company.DAOCompany;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase encargada de enviar la informacion al frontend de la aplicacion
 */
@Path( "/M02_Companies" )
public class M02_Companies {

    Gson gson = new Gson();
    ArrayList<Company> _coList = new ArrayList<>();
    Company _co = new Company();

    private final String MESSAGE_ERROR_INTERN = "Error Interno";
    private final String MESSAGE_EXCEPTION = "Excepcion";
    private final String MESSAGE_ERROR_PARAMETERDOESNTEXIST= "La parametros ingresados no Validos";






    @POST
    @Path("/AddCompanyPP")
    @Produces("application/json")
    @Consumes("application/json")
    /**
     * Metodo que recibe un DTOFullCompany y agrega una compañia especificada en el DTO
     * @param _dto posee todos los atributos necesarios para realizar el metodo
     * @return Response con status ok al encontrar la informacion solicitada
     */
    public Response addCompanyPP( DTOFullCompany _dto ) {
        Error error;
        Response.ResponseBuilder _rb = Response.status(Response.Status.OK );
        Logger logger = Logger.getLogger(M02_Companies.class.getName());
        logger.info("Objeto compania recibido en AddCompany" + _dto.get_idCompany() + " " +
                    _dto.get_name() + " "+ _dto.get_status() + " " + _dto.get_desc() + " " +
                    _dto.get_link() + " " + _dto.get_idUser() );
       try {

           MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany( ) ;
           Company _company = (Company) _mapper.CreateEntity( _dto );
           AddCompanyCommand _command = CommandsFactory.createAddCompanyCommand( _company );
           _command.execute();
           return _rb.build() ;

       }
       catch ( Exception e ) {
           error = new Error( MESSAGE_ERROR_INTERN );
           error.addError( MESSAGE_EXCEPTION,e.getMessage() );
           return Response.status(500).entity(error).build();
        }
    }




    @GET
    @Path("/GetAllPP")
    @Produces("application/json")
    /**
     * Metodo Response que devuelve todas las compañias registradas en el sistema
     * @return Response con status ok al encontrar la informacion solicitada y la lista de companias
     */
    public Response getAllCompaniesPP() {
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            GetAllCompaniesCommand _command = CommandsFactory.createGetAllCompaniesCommand();
            _command.execute();
            MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany();
            ArrayList< Entity > _comp = _command.ReturnList();
            List< DTOFullCompany >_dtoCo = _mapper.CreateDtoList( _comp );
            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();
        }

        catch ( Exception e ){
            error = new Error( MESSAGE_ERROR_INTERN );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
    }



    @POST
    @Path("/updateCompanyStatus")
    @Consumes("application/json")
    @Produces("text/plain")
    /**
     * Metodo que recibe un DTOIdStatusCompany y cambia el status de la compañia especificada en el DTO
     * @param _dto posee todos los atributos necesarios para realizar el metodo
     * @return Response con status ok al encontrar la informacion solicitada
     */
    public Response changeCompanyStatusPP( DTOIdStatusCompany _dto ) {
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {

            MapperIdStatusCompany _mapper =  MapperFactory.createMapperIdStatusCompany();
            Company _comp = (Company) _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createChangeStatusCommand( _comp );
            _command.execute();
            return _rb.build();
        }
        catch (Exception e){
            error = new Error( MESSAGE_ERROR_INTERN );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }

    }




    @GET
    @Path("/GetCompaniesByUserPP/{companyId}")
    @Consumes("application/json")
    @Produces("application/json")
      /**
     * Metodo que recibe el id de un usuario y devuelve las compañias en las que es administrador
     * @param id posee el id del usuario
     * @return Response con status ok al encontrar la informacion solicitada y una lista de companias
     */
    public Response getCompaniesByUserPP( @PathParam("companyId") int id ){
        DTOIdCompany _dto = DTOFactory.CreateDTOIdCompany(id);
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            if (id == 0){
                throw new InvalidParameterException();
            }
            MapperIdCompany _mapper = MapperFactory.createMapperIdCompany();
            Company _comp = (Company)_mapper.CreateEntity( _dto );
            GetCompanyByUserCommand _command = CommandsFactory.createGetCompanyByUserCommand( _comp );
            _command.execute();
            MapperFullCompany _mappCompany = MapperFactory.CreateMapperFullCompany();
            List < DTOFullCompany > _dtoCo = _mappCompany.CreateDtoList( _command.ReturnList() ) ;
            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();
        }
        catch ( InvalidParameterException e ){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_PARAMETERDOESNTEXIST );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
        catch (Exception e){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_INTERN );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
    }




    @PUT
    @Path("/Edit/CompanyPP")
    @Produces("application/json")
    @Consumes("application/json")
    /**
     * Metodo que recibe un DTOFullCompany y edita la compañia especificada en el DTO
     * @param _dto posee todos los atributos necesarios para realizar el metodo
     * @return Response con status ok al encontrar la informacion solicitada
     */
    public Response editCompanyPP( DTOFullCompany _dto ) {
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        String Status = String.valueOf( _dto.is_status() );

        try {
            MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany();
            Company _comp =(Company) _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createUpdateCompanyCommand( _comp );
            _command.execute();
            return _rb.build();
        }
        catch ( Exception e ){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_INTERN );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
    }

    @GET
    @Path("/GetCompaniesByResponsible/{idUser}")
    @Produces("application/json")
    /**
     * Metodo que consulta las companias de las cuales un usuario es responsable
     * @param _id id de usuario al cual esta asociado esas companias
     * @return Response con status ok al encontrar la informacion solicitada y la lista de companias
     */
    public Response getCompaniesByResponsible(@PathParam("idUser") int _id) throws  CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        DTOIdCompany _dto = DTOFactory.CreateDTOIdCompany( _id );
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );

        try {
            MapperIdCompany _mapper = MapperFactory.createMapperIdCompany();
            Company _comp = ( Company )_mapper.CreateEntity( _dto );
            GetCompanyResponsibleCommand _command = CommandsFactory.createGetCompanyByResponsibleCommand( _comp );
            _command.execute();
            MapperFullCompany _mappCompany = MapperFactory.CreateMapperFullCompany();
            List < DTOFullCompany > _dtoCo = _mappCompany.CreateDtoList( _command.ReturnList() ) ;
            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return rb.build();
    }
//------------------------------------------------------


  /*  @GET
    @Path("/CompanyDetails")
    @Produces("application/json")

    /**
     * Metodo que recibe el id de una compañia y devuelve todos los detalles de la misma
     * @param id el id de la compañia
     * @return Response con status ok al encontrar la informacion solicitada
     */
  /*  public Response getCompanyDetails(@QueryParam("id") int id) throws CompanyDoesntExistsException {
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
    }*/












}

