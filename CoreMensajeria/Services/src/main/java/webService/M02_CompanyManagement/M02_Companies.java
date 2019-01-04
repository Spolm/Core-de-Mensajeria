package webService.M02_CompanyManagement;

import DTO.M02_DTO.DTOCompanyWithOutIdAndLink;
import DTO.M02_DTO.DTOCompanyWithOut_Link;
import DTO.M02_DTO.DTOFullCompany;
import DTO.M03_DTO.DTOCampaignWithOut_Company;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.CompanyDAO;
import Entities.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;
import Exceptions.ParameterCantBeNullException;
import Factory.MapperFactory;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M02_Company.AddCompanyCommand;
import Logic.M02_Company.GetAllCompaniesCommand;
import Mappers.CompanyMapper.MapperCompanyWithOutIdAndLink;
import Mappers.CompanyMapper.MapperCompanyWithOut_Link;
import Mappers.CompanyMapper.MapperFullCompany;
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


    //Falta patronear este Metodo y Poner su DAO
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
    public Response addCompanyPP( DTOCompanyWithOutIdAndLink _dto ){

        Response.ResponseBuilder _rb = Response.status(Response.Status.OK );

       try {
           MapperCompanyWithOutIdAndLink mapper = MapperFactory.CreateMapperCompanyWithOutIdAndLink( ) ;
           Entity _company = mapper.CreateEntity( _dto );
           AddCompanyCommand _command = CommandsFactory.createAddCompanyCommand( _company );
           _command.execute();
           return _rb.build() ;

       } catch ( Exception e ) {
           _rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
           e.printStackTrace();
        }return _rb.build();
    }

    /**
     * Metodo Response que devuelve todas las compañias registradas en el sistema
     * @return Response con status ok al encontrar la informacion solicitada
     */

    @GET
    @Path("/GetAllPP")
    @Produces("application/json")

    public Response getAllCompaniesPP() {

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
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }
    }

    @POST
    @Path("/updateP/{companyId}")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response changeCompanyStatusPP( DTOCompanyWithOut_Link _dto ){

        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try {
            MapperCompanyWithOut_Link _mapper =  MapperFactory.CreateMapperCompanyWithOut_Link();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createChangeStatusCommand( _comp );
            _command.execute();

            //Agregue esto como un recurso desesperadooo

            Company _co = ( Company ) _comp;

            _rb.entity( gson.toJson( _co.get_status() ) ) ;
            return _rb.build();

        }
        catch (Exception e){
            return Response.status( 500 ).entity( e.getMessage() ).build();
        }

    }

    @GET
    @Path("/GetCompaniesPP")
    @Produces("application/json")

    /**
     * Metodo que recibe el id de un usuario y devuelve las compañias en las que es administrador
     * @param id el id del usuario
     * @return Response con status ok al encontrar la informacion solicitada
     */
    public Response getCompaniesByUserPP( DTOFullCompany _dto ){

        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );

        try {
            MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createGetCompanyCommand( _comp );
            _command.execute();
            DTOFullCompany _dtoCo = _mapper.CreateDto( _command.Return() ) ;

            _rb.entity( gson.toJson( _dtoCo ) ) ;
            return _rb.build();

        }
        catch (Exception e){


            return Response.status( 500 ).entity( e.getMessage() ).build();
        }

    }


    @PUT
    @Path("/Edit/CompanyPP/{companyId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editCompanyPP( DTOFullCompany _dto ){

        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );

        try {
            MapperFullCompany _mapper = MapperFactory.CreateMapperFullCompany();
            Entity _comp = _mapper.CreateEntity( _dto );
            Command _command = CommandsFactory.createUpdateCompanyCommand( _comp );
            _command.execute();

            return _rb.build();
        }

        catch ( Exception e ){

            return Response.status( 500 ).entity( e.getMessage() ).build();
        }


    }









//------------------------------------------------------














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

