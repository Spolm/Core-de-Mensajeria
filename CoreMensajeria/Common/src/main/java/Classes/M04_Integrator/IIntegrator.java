package Classes.M04_Integrator;


/**
 * Interfaz que contiene los métodos de utilizarán
 * todos los integradores en común.
 *
 * @author José Salas
 * @author Manuel Espinoza
 * @author José Cedeño
 * @see IIntegrator
 */
public interface IIntegrator {

    MessageIntegrator sendMessage(String msg, String address, String idMsg);
}
