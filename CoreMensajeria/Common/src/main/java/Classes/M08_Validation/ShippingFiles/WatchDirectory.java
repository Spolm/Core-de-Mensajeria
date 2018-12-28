package Classes.M08_Validation.ShippingFiles;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class WatchDirectory implements Runnable{
    private ArrayList<String> _paths;
    private static WatchDirectory watchDirectory;

    public WatchDirectory(ArrayList<String> _paths){
        this._paths = _paths;
    }

    public static  WatchDirectory getInstance(ArrayList<String> _paths){
        if (watchDirectory == null){
            watchDirectory = new WatchDirectory(_paths);
        }
        return watchDirectory;
    }

    public void run(){

        try {
            Logger LOGGER = Logger.getLogger(WatchDirectory.class.getName());
            final WatchService watchService = FileSystems.getDefault().newWatchService();

            for (String path : _paths) {
                final Path myDir = Paths.get(path);
                myDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            }

            while (true) {
                final WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        LOGGER.info("Created: " + event.context().toString());
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}

