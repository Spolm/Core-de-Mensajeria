package Modulo_1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/users")
public class LogIn {
    // The Java method will process HTTP GET requests
    @Path("/login")
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String login(@QueryParam("username") String username,@QueryParam("password") String password) {
        // Return some cliched textual content
        return "Has intentado ingresar";
    }
    @Path("/register")
    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON)
    public String register() {
        // Return some cliched textual content
        return "Has intentado registrarte";
    }
}