package webService.M10_Profile;

import Classes.M01_Login.User;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
public class M10_Profile {
    Gson gson= new Gson();
    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator(@PathParam( "username" ) String name) {
        User us = M10_ProfileService.getInstance().SearchUser(name);
        return Response.ok(gson.toJson(us)).build();
    }
}
