package webService.M08_MessageCoreManagement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/M08_MessageCore")
public class M08_MessageValidation {
    @GET
    @Path("/Validate")
    @Produces("text/plain")
    public String ValidateMessage() {
        // Return some cliched textual content
        return "Hello World23";
    }
}
