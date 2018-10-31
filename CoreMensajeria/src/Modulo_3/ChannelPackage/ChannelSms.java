package Modulo_3.ChannelPackage;

import Modulo_3.IntegratorPackage.Integrator;

import java.util.ArrayList;

public class ChannelSms extends Channel {
    public ChannelSms(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
