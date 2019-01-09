package webService.M04_Integrator;


import DTO.M04_Integrator.DTOIntegrator;
import Entities.M04_Integrator.Integrator;
import Exceptions.PersonalizedException;
import Logic.CommandsFactory;
import Logic.M04_Integrator.*;
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
    private MapperIntegrator _mapper = MapperFactory.createMapperIntegrator();

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
            DTOIntegrator integrator = _mapper.CreateDto(command.Return());

            response = Response.ok().entity(integrator).build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

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
            ArrayList<DTOIntegrator> integratorList =  (ArrayList<DTOIntegrator>)
                    _mapper.CreateDtoList(command.returnList());
            response = Response.ok().entity(integratorList).build();
        } catch (PersonalizedException e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

    /**
    *Método que nos permite obtener una lista de integradores por canal
    *
    * @param id
    * @return
    */
    @GET
    @Path("/channel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIntegratorByChannel(@PathParam("id") int id) {
        Response response;
        try {
            CommandGetIntegratorByChannel command = CommandsFactory.createCommandGetIntegratorByChannel(id);
            command.execute();
            ArrayList<DTOIntegrator> integratorList =  (ArrayList<DTOIntegrator>)
                    _mapper.CreateDtoList(command.returnList());
            response = Response.ok().entity(integratorList).build();
        } catch (PersonalizedException e) {
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
            DTOIntegrator integrator = _mapper.CreateDto(command.Return());
            response = Response.ok().entity("Integrador " + integrator.getId() + " deshabilitado").build();
        } catch (PersonalizedException e) {
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
            DTOIntegrator integrator = _mapper.CreateDto(command.Return());
            response = Response.ok().entity("Integrador habilitado").build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

}
