package webService.M04_Integrator;


import DTO.M04_Integrator.DTOIntegrator;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.IntegratorDAO;
import Logic.CommandsFactory;
import Logic.M04_Integrator.CommandDisableIntegrator;
import Logic.M04_Integrator.CommandEnableIntegrator;
import Logic.M04_Integrator.CommandGetAllIntegrator;
import Logic.M04_Integrator.CommandGetConcreteIntegrator;
import Mappers.M04_Integrator.MapperIntegrator;
import Mappers.MapperFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Clase que implementa los métodos PUT y GET para el funcionamiento
 * del servicio RESTful referido a los integradores del sistema.
 *
 * @author Kevin Martinez
 * @author Alexander Fernnadez
 * @author Braulio Picon
 */

@Path("/integrators")
public class M04_Integrator {

    MapperIntegrator _mapper = MapperFactory.createMapperIntegrator();

    private IntegratorDAO _integratorDAO = new IntegratorDAO();

    /**
     * Método que nos permite obtener la lista de todos
     * los integradores en el sistema y transformarlo a formato json.
     *
     * @return Lista de integradores
     * @see Integrator
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegrator() {
        Response response;
        try {
            CommandGetAllIntegrator command = CommandsFactory.createCommandGetAllIntegrators();
            command.execute();
            ArrayList<DTOIntegrator> _integrator =  (ArrayList<DTOIntegrator>)
                    _mapper.CreateDtoList(CommandsFactory.createCommandGetAllIntegrators().ReturnList());
            response = Response.ok().entity(_integrator).build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }






    /**
     * Método que nos permite obtener un integrador en concreto.
     *
     * @param id Id del integrador
     * @return Integrador buscado por id
     * @see Integrator
     */

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcreteIntegrator(@PathParam("id") int id) {
        Response response;
        try {
        CommandGetConcreteIntegrator command = CommandsFactory.createCommandGetConcreteIntegrator(id);
        command.execute();
        DTOIntegrator _integrator = _mapper.CreateDto(command.Return());

            response = Response.ok().entity(_integrator).build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

    /**
     * Método que nos permite deshabilitar un integrador
     *
     * @param id Id del integrador
     * @return Lista de integradores
     * @see Integrator
     */

    @PUT
    @Path("/disabled/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disableIntegrator(@PathParam("id") int id) {
        Response response;
        try {
            CommandDisableIntegrator command = CommandsFactory.createCommandDisableIntegrator(id);
            command.execute();
            DTOIntegrator _integrator = _mapper.CreateDto(command.Return());
            response = Response.ok().entity("Integrador deshabilitado").build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

    /**
     * Método que nos permite habilitar un integrador
     *
     * @param id Id del integrador
     * @return Lista de integradores
     * @see Integrator
     */

    @PUT
    @Path("/enabled/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enableIntegrator(@PathParam("id") int id) {
        Response response;
        try {
            CommandEnableIntegrator command = CommandsFactory.createCommandEnableIntegrator(id);
            command.execute();
            DTOIntegrator _integrator = _mapper.CreateDto(command.Return());
            response = Response.ok().entity("Integrador habilitado").build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

}
