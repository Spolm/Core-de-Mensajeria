package Classes.M03_Channel_Integrator.ChannelPackage;

import Classes.M03_Channel_Integrator.IntegratorPackage.Integrator;

import java.util.ArrayList;

public class ChannelEmail extends Channel {
    
    public ChannelEmail(int idChannel, String channelName, String channelDescription,
                        ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
