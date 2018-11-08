package Classes.M04_Channel_Integrator.ChannelPackage;

import Classes.M04_Channel_Integrator.IntegratorPackage.Integrator;

import java.util.ArrayList;


public class ChannelSms extends Channel {
    public ChannelSms(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
