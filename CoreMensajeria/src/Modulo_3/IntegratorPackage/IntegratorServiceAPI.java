package Modulo_3.IntegratorPackage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/integrators")

public class IntegratorServiceAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator() {
        List<Integrator> i = IntegratorService.getInstance().listIntegrator();
        return Response
                .ok()
                .entity(i)
                .build();
    }
}
