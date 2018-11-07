package webService.M10_Profile;

import Classes.M01_Login.User;
import Classes.M10_Profile.M10_Profile;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("/profile")
public class M10_ProfileService {
    Gson gson= new Gson();

    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "username" ) String name) { //Hay que cambiarle el nombre a esto
        ArrayList<User> us = M10_Profile.getInstance().searchUser(name);
        return Response.ok(gson.toJson(us)).build();
    }

    @Path("/edit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProfile(@FormParam("email") String email, @FormParam("phone") String phone,
                                @FormParam("location") String address) throws URISyntaxException {
        String success = M10_Profile.getInstance().editProfile(1, email, phone,address );
        java.net.URI location = new java.net.URI("http://localhost:4200/profile");
        return Response.temporaryRedirect(location).build();
    }
}
