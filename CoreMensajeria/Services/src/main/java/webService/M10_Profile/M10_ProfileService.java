package webService.M10_Profile;

import Classes.M01_Login.User;
import Classes.M10_Profile.EditFormHandler;
import Classes.M10_Profile.EditRequestBody;
import Classes.M10_Profile.FormErrorException;
import Classes.M10_Profile.M10_Profile;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("/profile")
public class M10_ProfileService {
    Gson _gson= new Gson();
    ArrayList<User> _us;

    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "username" ) String name) { //Hay que cambiarle el nombre a esto
        try {

            _us = M10_Profile.getInstance().searchUser(name);
        }
        catch(NullPointerException e)
        {
            _us = null;
            String mess="error al cargar el usuario";
        }
        catch (Exception e)
        {
            _us = null;

        }
        return Response.ok(_gson.toJson(_us)).build();
    }

    @Path("/edit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(EditRequestBody editBody) throws URISyntaxException {

        try{

            //Instanciamos nuestro validador
            EditFormHandler validator = new EditFormHandler(editBody);

            //Se valida que los datos sean correctos
            validator.validate();

            //Se procede a editar al usuario
            String success = M10_Profile.getInstance().editProfile( editBody.get_idUser(), editBody.get_emailUser(),
                    editBody.get_phoneUser(),editBody.get_addressUser() );
            return Response.ok(_gson.toJson(success)).build();

        }
        catch (FormErrorException e) {
            return Response.status(500).entity(e).build();
        }
        catch (Exception e){
            return Response.status(500).entity(e).build();
        }
    }
}
