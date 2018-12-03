package Classes.M04_Integrator;


/**
 * Interfaz que contiene los metodos de utilizaran
 * todos los integradores en comun.
 *
 * @Author José Salas
 * @Author Manuel Espinoza
 * @Author José Cedeño
 * @see IIntegrator
 */
public interface IIntegrator {

    MessageIntegrator sendMessage(String msg, String address, String idMsg);
}
