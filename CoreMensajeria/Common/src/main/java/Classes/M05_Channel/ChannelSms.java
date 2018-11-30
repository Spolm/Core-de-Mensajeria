package Classes.M05_Channel;

import Classes.M04_Integrator.Integrator;

import java.util.ArrayList;


public class ChannelSms extends Channel {
    public ChannelSms(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
