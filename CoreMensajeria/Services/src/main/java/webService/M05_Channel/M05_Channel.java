package webService.M05_Channel;

import DTO.M05_Channel.DTOChannel;
import Entities.M05_Channel.Channel;
import Exceptions.DatabaseConnectionProblemException;
import Logic.CommandsFactory;
import Logic.M05_Channel.CommandGetAllChannels;
import Mappers.M05_Channel.MapperChannel;
import Mappers.MapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
* Clase que implementa los métodos PUT y GET para el funcionamiento
* del servicio RESTful referido a los canales del sistema.
*
* @author Kevin Martinez
* @author Braulio Picon
* @author Alexander Fernandez
*/
@Path("/channel")
public class M05_Channel {

    final static Logger log = LogManager.getLogger("CoreMensajeria");
    private MapperChannel _mapper = MapperFactory.createMapperChannel();

    /**
     * Método que nos permite obtener una lista de
     * todos los canales en el sistema en formato Json.
     *
     * @see Channel
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listChannel() {
        //region Instrumentation Debug
        log.debug("Entrando a el metodo listChannel ServiceChannel");
        //endregion
        Response response;
        try {
            CommandGetAllChannels command = CommandsFactory.createCommandGetAllChannels();
            command.execute();
            ArrayList<DTOChannel> c = (ArrayList<DTOChannel>) _mapper.CreateDtoList(command.returnList());
            response = Response.ok().entity(c).build();
        //region Intrumentation Info
        log.info("Se ejecuto exitosamente el metodo listChannel ServiceChannel");
        //endregion
        } catch (DatabaseConnectionProblemException e) {
            response = Response.status(500).entity(e.getMessage()).build();
        //region Instrumentation Error
        log.error("El metodo listChannel arrojo la excepcion"+ e.getMessage()+"ServiceChannel");
        //endregion
        }
        //region Instrumentation Debug
        log.debug("Saliendo del metodo listChannel ServiceChannel ");
        //endregion
        return response;
    }
}