package Logic.M08_SendMessage.XMLManagment;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Logic.Command;
import Logic.CommandsFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.*;
import java.util.ArrayList;
/**
 * Clase demonio encargado de vigilar las carpetas de las compañías
 * e invocar su procesamiento.
 */
public class WatchDirectory implements Runnable{

    private final static Logger log = LogManager.getLogger("CoreMensajeria");
    private ArrayList<String> _paths;
    private static WatchDirectory watchDirectory;
    private Command<VerifiedParameter> _commandProcessXML;

    /**
     * Consturctor que inicializa una instancia de la clase WatchDirectory
     * con las rutas de las carpetas a vigilar.
     * @param _paths Rutas de las carpetas a vigilar.
     */
    public WatchDirectory( ArrayList<String> _paths ){
        this._paths = _paths;
    }

    /**
     * Patrón Singleton para instanciar un solo objeto
     * de la clase WatchDirectory
     * @param _paths Rutas de las carpetas a vigilar.
     * @return El objeto de la clase WatchDirectory
     */
    public static  WatchDirectory getInstance( ArrayList<String> _paths ) {
        if ( watchDirectory == null ){
            watchDirectory = new WatchDirectory( _paths );
        }
        return watchDirectory;
    }

    /**
     * Método principal del demonio, monitorea unicamente la creación
     * de nuevas entradas dentro de los directorios definidos
     * en el constructor de la clase.
     *
     * Emplea el comando ProcessXML para procesar el archivo XML para su
     * posterior envío.
     *
     * @see CommandProcessXML
     */
    public void run() {
        try {
            final WatchService watchService = FileSystems.getDefault().newWatchService();

            for ( String path : _paths ) {
                final Path myDir = Paths.get( path );
                myDir.register( watchService, StandardWatchEventKinds.ENTRY_CREATE );
            }
            while ( true ) {
                log.debug("Inicio de la vigilancia por parte del demonio");
                final WatchKey key = watchService.take();
                final Watchable watchable = key.watchable();
                final Path directory = (Path) watchable;

                for ( WatchEvent<?> event : key.pollEvents() ) {

                    log.debug("Creado: " + event.context().toString()
                            + " en el directorio " + directory );

                    if(event.context().toString().endsWith(".xml")) {
                        _commandProcessXML = CommandsFactory
                                .createCommandProcessXML(directory + "/" + event.context().toString());
                        _commandProcessXML.execute();
                        log.info("Se ha procesado el XML");
                        VerifiedParameter verifiedParameter = _commandProcessXML.Return();
                        Command _commandSendMsg = CommandsFactory.createSendMessage(verifiedParameter);
                        _commandSendMsg.execute();
                    }
                }
                key.reset();
            }
        } catch ( Exception e ) {
            log.error("Error inesperado de tipo "
                    + e.getClass().getName() );
        }
    }
}
