import webService.M03_CampaignManagement.M03_Campaigns;
import webService.M05_Channel.M05_Channel;
import webService.M04_Integrator.M04_Integrator;
import webService.M01_Login.M01_Login;
import webService.M01_Login.M01_User;
import webService.M02_CompanyManagement.M02_Companies;
import webService.M06_DataOrigin.M06_Application;
import webService.M07_Template.M07_Parameter;
import webService.M07_Template.M07_Template;

import webService.M08_MessageCoreManagement.M08_MessageValidation;
import webService.M09_StatisticsManagement.M09_Statistics;
import webService.M10_Profile.M10_GeographicalRegion;
import webService.M10_Profile.M10_Profile;

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
        h.add( M01_Login.class );
        h.add( M01_User.class );
        h.add(M02_Companies.class);
        h.add(M03_Campaigns.class);
        h.add( M06_Application.class );
        h.add( M07_Template.class );
        h.add(M07_Parameter.class);
        h.add(M04_Integrator.class);
        h.add(M05_Channel.class);
        h.add(M08_MessageValidation.class);
        h.add(M09_Statistics.class );
        h.add(M10_Profile.class);
        h.add(M10_GeographicalRegion.class);
        return h;
    }
}
