package webService.M10_Profile;

import Entities.M01_Login.User;
import Entities.M01_Login.UserDAO;
import Entities.M02_Company.Company;
import Entities.M10_Profile.*;
import Exceptions.FormErrorException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Path("/profile")
public class M10_ProfileService {
    private Gson _gson = new Gson();
    private User _us;
    private ArrayList<User> _users;
    private ArrayList<Company> _comp;
    private M10_Profile _daoProfile;
    private UserDAO _daoUser;
    private ArrayList<Rol> _rols;


    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRoles(){
        _daoProfile = new M10_Profile();
        _rols = _daoProfile.getAllRoles();
        return Response.ok().entity(_rols).build();
    }
    @Path("/user/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam("id") int id) { //Hay que cambiarle el nombre a esto
        try {
            _daoUser = new UserDAO();
            _us = _daoUser.findByUsernameId(id);
        } catch (NullPointerException e) {
            _us = null;
            String mess = "error al cargar el usuario";
        } catch (Exception e) {
            _us = null;

        }
        return Response.ok(_gson.toJson(_us)).build();
    }

    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(EditRequestBody editBody) throws URISyntaxException {

        try {

            _daoProfile = new M10_Profile();

            //Instanciamos nuestro validador
            EditFormHandler validator = new EditFormHandler(editBody);

            //Se valida que los datos sean correctos
            validator.validate();

            //Se procede a editar al usuario
            String success = _daoProfile.editProfile(editBody.get_idUser(), editBody.get_emailUser(),
                    editBody.get_phoneUser(), editBody.get_addressUser());

            //region Instrumentation DEBUG
            Logger logger = Logger.getLogger("MyLog");
            logger.setUseParentHandlers(false);
            FileHandler fh;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("C:/Users/ruben/Desktop/MyLogFile.log");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

                // the following statement is used to log any messages
                logger.info(editBody.get_idUser() + " - " + editBody.get_emailUser()+ " - " +
                        editBody.get_phoneUser()+ " - " + editBody.get_addressUser());

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.info("Exit edit user");
            //endregion

            return Response.ok(_gson.toJson(success)).build();

        } catch (FormErrorException e) {
            return Response.status(500).entity(e).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(CreateUserRequestBody createBody) throws URISyntaxException {

        try {

            _daoProfile = new M10_Profile();

            //Instanciamos nuestro validador
            CreateFormHandler validator = new CreateFormHandler(createBody);
            validator.validate();

            //Se procede a editar al usuario
            String success = _daoProfile.addUser(createBody);
            return Response.ok(_gson.toJson(success)).build();

        } catch (FormErrorException e) {
            return Response.status(500).entity(e).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }

    @Path("/listcompanies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCompanies()
    {
      try
      {
          _daoProfile= new M10_Profile();
          _comp= _daoProfile.getCompanies();
      } catch (NullPointerException e) {
          _comp = null;
          String mess = "error al cargar el usuario";
      } catch (Exception e) {
          _comp = null;
      }
      finally {
          return Response.ok(_gson.toJson(_comp)).build();
      }
    }

    @Path("/listusers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers()
    {
        try
        {
            _daoProfile= new M10_Profile();
            _users= _daoProfile.getUsers();
        } catch (NullPointerException e) {
            _users = null;
            return Response.status(500).entity(e).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
        finally {
            return Response.ok(_gson.toJson(_users)).build();
        }
    }
}
