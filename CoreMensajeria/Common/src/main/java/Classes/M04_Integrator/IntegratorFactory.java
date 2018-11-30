package Classes.M04_Integrator;

public class IntegratorFactory {

    /**
     * Metodo que se encarga de crear un integrador en concreto
     * La creacion de este tipo de integrador dependera del integratorType
     * que es recibido por parametro
     * @param  integratorType  el tipo de integrador, del cual dependera la creacion del objeto
     * @param  idIntegrator el id del integrador a crear
     * @param  nameIntegrator nombre del integrador
     * @param  messageCost costo por mensaje del integrador
     * @param  threadCapacity capacidad de hilos que es capaz de soportar el integrador
     * @param  apiIntegrator un codigo de seguridad que se requiere para validar que
     * el integrador sea el correcto
     * @param  enabled Que nos indica cual es el estado del integrador
     * @return Un objeto integrador con las caracteristicas enviadas por parametro
     * @see         Integrator
     */

    public static Integrator getIntegrator(String integratorType, int idIntegrator, String nameIntegrator,
                                    float messageCost, int threadCapacity, String apiIntegrator, boolean enabled) {
        if (integratorType == null) {
            return null;
        }
        if (integratorType.equalsIgnoreCase("MOVISTAR")) {
            return new Movistar(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);

        } else if (integratorType.equalsIgnoreCase("DIGITEL")) {
            return new Digitel(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);

        } else if (integratorType.equalsIgnoreCase("MOVILNET")) {
            return new Movilnet(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);

        } else if (integratorType.equalsIgnoreCase("MAILCHIMP")) {
            return new MailChimp(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);

        } else if (integratorType.equalsIgnoreCase("AWEBER")) {
            return new Aweber(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);

        } else if (integratorType.equalsIgnoreCase("INFUSIONSOFT")) {
            return new InfusionSoft(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);
        }

        return null;
    }
}
