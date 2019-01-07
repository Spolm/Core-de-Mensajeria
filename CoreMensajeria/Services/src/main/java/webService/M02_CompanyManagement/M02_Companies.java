package webService.M02_CompanyManagement;

import DTO.DTOFactory;
import DTO.M02_DTO.*;
import DTO.M03_DTO.DTOCampaignWithOut_Company;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.CompanyDAO;
import Entities.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import Exceptions.M07_Template.InvalidParameterException;
import Exceptions.ParameterCantBeNullException;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M02_Company.AddCompanyCommand;
import Logic.M02_Company.GetAllCompaniesCommand;
import Logic.M02_Company.GetCompanyByUserCommand;
import Mappers.CompanyMapper.*;
import Mappers.MapperFactory;
import webService.M01_Login.Error;
import Persistence.M02_Company.DAOCompany;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;

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

    @GET
    @Path("/CompanyDetails")
    @Produces("application/json")

    /**
     * Metodo que recibe el id de una compañia y devuelve todos los detalles de la misma
     * @param id el id de la compañia
     * @return Response con status ok al encontrar la informacion solicitada
     */
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

    /**
     * Metodo que recibe el id de un usuario y devuelve las compañias en las que es administrador
     * @param id el id del usuario
     * @return Response con status ok al encontrar la informacion solicitada
     */
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
    @Path("/GetCompaniesByResponsible")
    @Produces("application/json")
    public Response getCompaniesByResponsible(@QueryParam("id") int id) throws  CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        CompanyDAO co = new CompanyDAO();
        try {
            _coList = co.companyListResponsible(id);
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

    /**
     * Metodo Response que devuelve todas las compañias registradas en el sistema
     * @return Response con status ok al encontrar la informacion solicitada
     */
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

    /**
     * Metodo que recibe el id de una compañia y la activa o desactiva
     * @param id id de la compañia que se va a activar/desactivar
     * @return Response con sttus ok al cambiar con exito el status de la compañia
     * @throws CompanyDoesntExistsException
     */
    //region Cambiar Status Compañia
    //TODO crear excepcion para este metodo
    @GET
    @Path("/update/{companyId}/{status}")
    //@Consumes("application/json")
    @Produces("text/plain")
    public Response changeCompanyStatus(@PathParam("companyId") int id , @PathParam("status") Boolean status)
                                        throws CompanyDoesntExistsException {
        Response.ResponseBuilder rb = Response.status(Response.Status.ACCEPTED);
        Boolean flag;
        CompanyDAO co = new CompanyDAO();
        try {
            flag = co.changeStatus(id,status);
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

  /*  @POST
    @Path("/AddCompany")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCompany(Company _company){

        Response.ResponseBuilder rb = Response.status(Response.Status.OK);
        CompanyDAO _companyDAO = new CompanyDAO();

        try {
            _companyDAO.createCompany(_company);
        } catch (CompanyDoesntExistsException e) {
            rb = Response.status(Response.Status.NO_CONTENT);
            e.printStackTrace();
        }
        catch (ParameterCantBeNullException e) {
            rb = Response.status(Response.Status.NOT_ACCEPTABLE);
            e.printStackTrace();
        }
        catch (Exception e) {
            rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }return rb.build();
    }

*/
    @PUT
    @Path("/Edit/Company/{companyId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editCompany(Company _company,@PathParam("companyId") int id){
        Response.ResponseBuilder rb = Response.status(Response.Status.OK);
        CompanyDAO _companyDAO = new CompanyDAO();

        try {
            _companyDAO.updateCompany(id,_company);
        } catch (CompanyDoesntExistsException e) {
            rb = Response.status(Response.Status.NO_CONTENT);
            e.printStackTrace();
        }
        catch (ParameterCantBeNullException e) {
            rb = Response.status(Response.Status.NOT_ACCEPTABLE);
            e.printStackTrace();
        }
        catch (Exception e) {
            rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }return rb.build();
    }





//---------- Region con los metodos ya con sus patrones

/* RAFA
    @POST
    @Path("/AddCompany")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCompany( Company _company ) throws CompanyDoesntExistsException, ParameterCantBeNullException {
        Response.ResponseBuilder _rb = Response.status(Response.Status.OK);
        DAOCompany _companyDAO = new DAOCompany();

        try {
            _companyDAO.create(_company);
        } catch (Exception e) {
            _rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }return _rb.build();
    }*/

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
           if( _dto.get_desc() == ""  ||  _dto.get_desc() == "") {
               throw new InvalidParameterException();
           }

           MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany( ) ;
           Entity _company = _mapper.CreateEntity( _dto );
           AddCompanyCommand _command = CommandsFactory.createAddCompanyCommand( _company );
           _command.execute();
           return _rb.build() ;

       }catch ( ParameterCantBeNullException e){
           e.printStackTrace();
           error = new Error( MESSAGE_ERROR_PARAMETERDOESNTEXIST );
           error.addError( MESSAGE_EXCEPTION,e.getMessage() );
           return Response.status(500).entity(error).build();
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
     * @return Response con status ok al encontrar la informacion solicitada
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
        String Status = String.valueOf( _dto.is_status() );
        try {
            if ( _dto.get_idCompany() == 0 || Status == "" ){
                throw new InvalidParameterException();
            }
            MapperIdStatusCompany _mapper =  MapperFactory.createMapperIdStatusCompany();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createChangeStatusCommand( _comp );
            _command.execute();
           /* //Agregue esto como un recurso desesperadooo
             Company _co = ( Company ) _comp;
            _rb.entity( gson.toJson( _co.get_status() ) ) ;*/
            return _rb.build();
        }
        catch (ParameterCantBeNullException e){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_PARAMETERDOESNTEXIST );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
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
     * @return Response con status ok al encontrar la informacion solicitada
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
            Entity _comp = _mapper.CreateEntity( _dto );
            GetCompanyByUserCommand _command = CommandsFactory.createGetCompanyByUserCommand( _comp );
            _command.execute();
            MapperFullCompany _mappCompany = MapperFactory.CreateMapperFullCompany();
            List < DTOFullCompany > _dtoCo = _mappCompany.CreateDtoList( _command.ReturnList() ) ;
            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();
        }
        catch ( ParameterCantBeNullException e ){
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
            if ( _dto.get_idCompany() == 0 || _dto.get_name() == "" || _dto.get_desc() == "" || _dto.get_link() == ""
                    || Status == "" || _dto.get_idUser() == 0 ) {

                throw new InvalidParameterException();
            }
            MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createUpdateCompanyCommand( _comp );
            _command.execute();
            return _rb.build();
        }
        catch ( ParameterCantBeNullException e ){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_PARAMETERDOESNTEXIST );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
        catch ( Exception e ){
            e.printStackTrace();
            error = new Error( MESSAGE_ERROR_INTERN );
            error.addError( MESSAGE_EXCEPTION,e.getMessage() );
            return Response.status(500).entity(error).build();
        }
    }


//------------------------------------------------------














    /*@POST
    @Path("/EditCompany/{idCompany}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCompany (@FormParam("nameCompany") String name,
                                 @FormParam(descriptionCompany) String description,
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
        String query = "INSERT INTO company ( com_name, com_description, com_status, com_user_id )
        VALUES "+name+","+description+","+status+","+user;


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

