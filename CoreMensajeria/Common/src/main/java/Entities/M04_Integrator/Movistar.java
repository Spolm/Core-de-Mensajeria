package Entities.M04_Integrator;

/**
 * Clase concreta de tipo Integrador que nos permite tener
 * acceso a métodos concretos para realizar el envío de mensajes.
 *
 * @author José Salas
 * @author Manuel Espinoza
 * @author José Cedeño
 * @see Integrator
 */

public class Movistar extends Integrator {

    /**
     * Constructor de la clase Movistar
     *
     * @param idIntegrator   Id del Integrador.
     * @param threadCapacity Capacidad de Hilos que soporta el integrador
     * @param messageCost    Costo por mensaje
     * @param nameIntegrator Nombre del integrador
     * @param apiIntegrator  Token del Integrador
     * @param enabled        Permite saber el estado en el que se encuentra el integrador
     * @see Integrator
     */

    public Movistar(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);
    }

    /**
     * Metodo que se encarga de enviar el Mensaje
     *
     * @param msg     Mensaje que se va a enviar.
     * @param address Dirección a la que se va a enviar.
     * @param idMsg   id del mensaje.
     * @see MessageIntegrator
     */

    @Override
    public MessageIntegrator sendMessage(String msg, String address, String idMsg) {
        if (this.isEnabled()) {
            try {
                Thread.sleep(2000);
                MessageIntegrator message = new MessageIntegrator(msg, address, idMsg);
                message.setSend(true);
                return message;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            MessageIntegrator message = new MessageIntegrator(msg, address, idMsg);
            message.setSend(false);
            return message;
        }
        return null;
    }
}
