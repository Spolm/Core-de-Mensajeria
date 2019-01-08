package Registry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Registry {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties properties = new Properties();
    private static Registry registryInstance;
    private String specificKey;

    private Registry() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Registry getInstance(){
        if(registryInstance == null){
            registryInstance = new Registry();
        }
        return registryInstance;
    }

    public String getProperty(String key){
        String databaseName = properties.getProperty("databaseName");
        String fullKey = databaseName + "." + key;
        String property = properties.getProperty(fullKey);
        if (property == null || property.trim().length() == 0) {
            //Throw Exeption later
            // ...
            property = null;
        }
        return property;
    }

    public String getSpecificKey(){

        return properties.getProperty("specificKey");
    }

    public void setSpecificKey(String specificKey) {
        //Setear en dao.properies
        // ...
        this.specificKey = specificKey;
    }
}
