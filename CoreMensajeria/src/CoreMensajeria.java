import Modulo_3.ChannelPackage.ChannelServiceAPI;
import Modulo_3.IntegratorPackage.IntegratorServiceAPI;
import webService.M01_Login.M01_Login;
import webService.HelloWorld;
import webService.M01_Login.M01_User;
import webService.M02_CompanyManagement.M02_Companies;
import webService.M06_DataOrigin.M06_Application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class CoreMensajeria extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( HelloWorld.class );
        h.add( M01_Login.class );
        h.add( M01_User.class );
        h.add(M02_Companies.class);
        h.add( M06_Application.class );
        h.add(IntegratorServiceAPI.class);
        h.add(ChannelServiceAPI.class);
        return h;
    }
}