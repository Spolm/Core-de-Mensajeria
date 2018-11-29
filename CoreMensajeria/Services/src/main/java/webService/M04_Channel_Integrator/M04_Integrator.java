package webService.M04_Channel_Integrator;
import Classes.M04_Channel_Integrator.IntegratorPackage.Integrator;
import Classes.M04_Channel_Integrator.IntegratorPackage.IntegratorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/integrators")
public class M04_Integrator {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator() {
        List<Integrator> i = IntegratorService.getInstance().listIntegrator();
        return Response
                .ok()
                .entity(i)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcreteIntegrator(@PathParam( "id" ) int id) {
        Integrator i = IntegratorService.getInstance().getConcreteIntegrator(id);
        return Response
                .ok()
                .entity(i)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enabledIntegrator(@PathParam( "id" ) int id) {
        IntegratorService.getInstance().enabledIntegrator(id);
        return Response
                .ok()
                .entity("Se modifico")
                .build();
    }

}
