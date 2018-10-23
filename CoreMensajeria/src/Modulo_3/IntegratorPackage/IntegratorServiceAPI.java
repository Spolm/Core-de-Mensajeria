package Modulo_3.IntegratorPackage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/integrators")

public class IntegratorServiceAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listIntegrator() {
        return IntegratorService.getInstance().listIntegrator().toString();
    }
}
