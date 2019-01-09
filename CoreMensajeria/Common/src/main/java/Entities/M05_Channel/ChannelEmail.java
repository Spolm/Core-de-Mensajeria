package Entities.M05_Channel;

import Entities.Entity;
import Entities.M04_Integrator.Integrator;

import java.util.ArrayList;

/**
 * Clase ChannelEmail que nos permite crear un canal en concreto.
 *
 * @author Kevin Martinez
 * @author Braulio Picon
 * @author Alexander Fernandez
 * @see Channel
 */

public class ChannelEmail extends Channel {

    public ChannelEmail(int idChannel, String channelName, String channelDescription,
                        ArrayList<Entity> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }

    public ChannelEmail(int idChannel, String channelName, String channelDescription) {
        super(idChannel, channelName, channelDescription);
    }
}
