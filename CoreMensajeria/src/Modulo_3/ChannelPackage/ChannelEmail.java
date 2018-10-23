package Modulo_3.ChannelPackage;

import Modulo_3.IntegratorPackage.Integrator;

import java.util.ArrayList;

public class ChannelEmail extends Channel {
    public ChannelEmail(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
