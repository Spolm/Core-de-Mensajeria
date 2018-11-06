package Classes.M03_Channel_Integrator.ChannelPackage;

import Classes.M03_Channel_Integrator.IntegratorPackage.Integrator;

import java.util.ArrayList;


public class ChannelFactory {
    public Channel getChannel(int idChannel, String nameChannel,
                              String descriptionChannel, ArrayList<Integrator> integrators) {
        if (nameChannel == null)
            return null;
        if (nameChannel.equalsIgnoreCase("SMS"))
            return new ChannelSms(idChannel, nameChannel, descriptionChannel, integrators);
        else if (nameChannel.equalsIgnoreCase("EMAIL"))
            return new ChannelEmail(idChannel, nameChannel, descriptionChannel, integrators);
        return null;
    }
}