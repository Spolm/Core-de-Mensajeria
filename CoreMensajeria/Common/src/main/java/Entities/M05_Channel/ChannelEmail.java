package Entities.M05_Channel;

import Entities.M04_Integrator.Integrator;

import java.util.ArrayList;

/**
 * Clase ChannelEmail que nos permite crear un canal en concreto.
 *
 * @author José Salas
 * @author Manuel Espinoza
 * @author José Cedeño
 * @see Channel
 */

public class ChannelEmail extends Channel {

    public ChannelEmail(int idChannel, String channelName, String channelDescription,
                        ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
