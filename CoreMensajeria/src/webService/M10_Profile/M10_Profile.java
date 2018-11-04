package webService.M10_Profile;

import Classes.User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
public class M10_Profile {
    Gson gson= new Gson();

    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "username" ) String name) { //Hay que cambiarle el nombre a esto
        User us = M10_ProfileService.getInstance().searchUser(name);
        return Response.ok(gson.toJson(us)).build();
    }

    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProfile(@FormParam("id") String id, @FormParam("username") String username,
                                @FormParam("email") String email, @FormParam("phone") String phone,
                                @FormParam("country") String country, @FormParam("date_of_birth") String birthday,
                                @FormParam("city") String city, @FormParam("address") String address) {
        String success = M10_ProfileService.getInstance().editProfile( id, username, email, phone, country, birthday, city,
                address );
        return Response.ok("{'message':'"+success+"', }").build();
    }
}
