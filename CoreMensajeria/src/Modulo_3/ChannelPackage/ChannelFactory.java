package Modulo_3.ChannelPackage;

import Modulo_3.IntegratorPackage.Integrator;

import java.util.ArrayList;


public class ChannelFactory {
    public Channel getChannel(int idChannel, String nameChannel, String descriptionChannel, ArrayList<Integrator> integrators) {
        if (nameChannel == null)
            return null;
        if (nameChannel.equalsIgnoreCase("SMS"))
            return new ChannelSms(idChannel, nameChannel, descriptionChannel, integrators);
        else if (nameChannel.equalsIgnoreCase("EMAIL"))
            return new ChannelEmail(idChannel, nameChannel, descriptionChannel, integrators);
        return null;
    }
}