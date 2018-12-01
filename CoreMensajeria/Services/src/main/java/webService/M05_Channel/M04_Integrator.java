package webService.M05_Channel;

import Classes.M04_Integrator.Integrator;
import Classes.M04_Integrator.IntegratorDAO;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/integrators")
public class M04_Integrator {

    private IntegratorDAO _integratorDAO = new IntegratorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator() {
        try {
            ArrayList<Integrator> i = _integratorDAO.listIntegrator();
            return Response.ok().entity(i).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcreteIntegrator(@PathParam("id") int id) {
        try {
            Integrator i = _integratorDAO.getConcreteIntegrator(id);
            return Response.ok().entity(i).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (IntegratorNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/disabled/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disableIntegrator(@PathParam("id") int id) {
        try {
            _integratorDAO.disableIntegrator(id);
            return Response.ok().entity("Integrador deshabilitado").build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (IntegratorNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/enabled/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enableIntegrator(@PathParam("id") int id) {
        try {
            _integratorDAO.enableIntegrator(id);
            return Response.ok().entity("Integrador habilitado").build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (IntegratorNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

}
