package Classes.M05_Channel;

import Classes.M04_Integrator.Integrator;

import java.util.ArrayList;


/**
 * Clase que se encarga de la creacion de los canales
 *
 * @Author José Salas
 * @Author Manuel Espinoza
 * @Author José Cedeño
 */

public class ChannelFactory {

    /**
     * Metodo que se encarga de crear un canal en concreto
     * La creacion de este tipo de canal dependera del nameChannel
     * que es recibido por parametro
     *
     * @param idChannel          id del canal a crear
     * @param nameChannel        nombre del canal a crear
     * @param descriptionChannel Breve descripcion del canal
     * @param integrators        Integradores que perteneces a esta canal
     * @return Un objeto Channel con las caracteristicas enviadas por parametro
     * @see Channel
     */

    public static Channel getChannel(int idChannel, String nameChannel, String descriptionChannel, ArrayList<Integrator> integrators) {
        if (nameChannel == null)
            return null;
        if (nameChannel.equalsIgnoreCase("SMS"))
            return new ChannelSms(idChannel, nameChannel, descriptionChannel, integrators);
        else if (nameChannel.equalsIgnoreCase("EMAIL"))
            return new ChannelEmail(idChannel, nameChannel, descriptionChannel, integrators);
        return null;
    }

}