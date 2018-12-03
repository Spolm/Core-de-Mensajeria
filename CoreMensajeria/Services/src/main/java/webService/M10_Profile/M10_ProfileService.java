package webService.M10_Profile;

import Classes.M01_Login.User;
import Classes.M02_Company.Company;
import Classes.M10_Profile.*;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("/profile")
public class M10_ProfileService {
    Gson _gson = new Gson();
    ArrayList<User> _us;
    ArrayList<Company> _comp;
    private M10_Profile _dao;

    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam("username") String name) { //Hay que cambiarle el nombre a esto
        try {
            _dao = new M10_Profile();
            _us = _dao.searchUser(name);
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

            _dao = new M10_Profile();

            //Instanciamos nuestro validador
            EditFormHandler validator = new EditFormHandler(editBody);

            //Se valida que los datos sean correctos
            validator.validate();

            //Se procede a editar al usuario
            String success = _dao.editProfile(editBody.get_idUser(), editBody.get_emailUser(),
                    editBody.get_phoneUser(), editBody.get_addressUser());
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

            //Instanciamos nuestro validador
            CreateFormHandler validator = new CreateFormHandler(createBody);
            validator.validate();

            //Se procede a editar al usuario
            String success = _dao.addUser(createBody);
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
          _dao= new M10_Profile();
          _comp= _dao.getCompanies();
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
}
