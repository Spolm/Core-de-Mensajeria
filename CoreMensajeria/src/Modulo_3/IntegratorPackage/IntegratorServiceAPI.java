package Modulo_3.IntegratorPackage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/integrator")

public class IntegratorServiceAPI {

    @GET
    @Produces("text/plain")
    public String listIntegrator(){
        return IntegratorService.getInstance().listIntegrator().toString();
    }
}
