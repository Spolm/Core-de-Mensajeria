package webService.M04_Integrator;

import Classes.M04_Integrator.Integrator;
import Classes.M04_Integrator.IntegratorDAO;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Clase que implementa los métodos PUT y GET para el funcionamiento
 * del servicio RESTful referido a los integradores del sistema.
 * @Author José Salas
 * @Author Manuel Espinoza
 * @Author José Cedeño
 */

@Path("/integrators")
public class M04_Integrator {

    private IntegratorDAO _integratorDAO = new IntegratorDAO();

    /**
     * Obtiene la lista de integradores en formato Json.
     * @return #### FALTA ####
     * @see         Integrator
     */

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

    /**
     * Obtiene un integrador en concreto en formato Json.
     * @return #### FALTA ####
     * @see         Integrator
     */

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

    /**
     * Inhabilita un integrador en especifico según su Id.
     * @return #### FALTA ####
     * @see         Integrator
     */

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

    /**
     * Habilita un integrador en especifico según su Id.
     * @return #### FALTA ####
     * @see         Integrator
     */

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
