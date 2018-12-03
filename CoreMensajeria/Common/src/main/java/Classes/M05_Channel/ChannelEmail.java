package Classes.M05_Channel;

import Classes.M04_Integrator.Integrator;

import java.util.ArrayList;

/**
 * Clase ChannelEmail que nos permite crear un canal en concreto.
 *
 * @Author José Salas
 * @Author Manuel Espinoza
 * @Author José Cedeño
 * @see Channel
 */

public class ChannelEmail extends Channel {

    public ChannelEmail(int idChannel, String channelName, String channelDescription,
                        ArrayList<Integrator> integrators) {
        super(idChannel, channelName, channelDescription, integrators);
    }
}
