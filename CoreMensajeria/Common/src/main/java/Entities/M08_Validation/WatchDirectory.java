package Entities.M08_Validation;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandsFactory;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class WatchDirectory implements Runnable{
    private ArrayList<String> _paths;
    private static WatchDirectory watchDirectory;
    private Command _commandProcessXML;

    public WatchDirectory( ArrayList<String> _paths ){
        this._paths = _paths;
    }


    public static  WatchDirectory getInstance( ArrayList<String> _paths ) {
        if ( watchDirectory == null ){
            watchDirectory = new WatchDirectory( _paths );
        }
        return watchDirectory;
    }

    /**
     * Método principal del demonio, monitorea unicamente la creación de nuevas entradas dentro de los
     * directorios definidos en el constructor de la clase.
     */
    public void run() {
        try {
            Logger log = Logger.getLogger(WatchDirectory.class.getName());
            final WatchService watchService = FileSystems.getDefault().newWatchService();

            for ( String path : _paths ) {
                final Path myDir = Paths.get( path );
                myDir.register( watchService, StandardWatchEventKinds.ENTRY_CREATE );
            }
            while ( true ) {
                final WatchKey key = watchService.take();
                final Watchable watchable = key.watchable();
                final Path directory = (Path) watchable;

                for ( WatchEvent<?> event : key.pollEvents() ) {

                    log.info("Created: " + event.context().toString() + " in directory " + directory );

                    if(directory.endsWith(".xml")) {
                        _commandProcessXML = CommandsFactory
                                .createCommandProcessXML(directory + "/" + event.context().toString());
                        _commandProcessXML.execute();
                    }

                }
                key.reset();
            }
        } catch ( Exception e ) {
            System.out.println( "Error Exc: " + e.toString() );
        }
    }
}
