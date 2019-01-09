package Entities.M04_Integrator;
import Entities.Entity;

/**
 * Clase abstracta Integrador que implementa la interfaz IIntegrador
 * que nos proporciona los métodos para trabajar con los integradores en concreto.
 *
 * @author José Salas
 * @author Manuel Espinoza
 * @author José Cedeño
 * @see IIntegrator
 */

public abstract class Integrator  extends Entity implements IIntegrator{
    private int threadCapacity;
    private float messageCost;
    private String nameIntegrator;
    private String apiIntegrator;
    private boolean enabled;

    /**
     * Constructor de la clase Integrator
     *
     * @param idIntegrator   Id del Integrador.
     * @param threadCapacity Capacidad de Hilos que soporta el integrador
     * @param messageCost    Costo por mensaje
     * @param nameIntegrator Nombre del integrador
     * @param apiIntegrator  Token del Integrador
     * @param enabled        Permite saber el estado en el que se encuentra el integrador
     * @see Integrator
     */

    public Integrator(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        this.set_id(idIntegrator);
        this.threadCapacity = threadCapacity;
        this.messageCost = messageCost;
        this.nameIntegrator = nameIntegrator;
        this.apiIntegrator = apiIntegrator;
        this.enabled = enabled;
    }

    public int getThreadCapacity() {
        return threadCapacity;
    }

    public void setThreadCapacity(int threadCapacity) {
        this.threadCapacity = threadCapacity;
    }

    public float getMessageCost() {
        return messageCost;
    }

    public void setMessageCost(float messageCost) {
        this.messageCost = messageCost;
    }

    public String getNameIntegrator() {
        return nameIntegrator;
    }

    public void setNameIntegrator(String nameIntegrator) {
        this.nameIntegrator = nameIntegrator;
    }

    public String getApiIntegrator() {
        return apiIntegrator;
    }

    public void setApiIntegrator(String apiIntegrator) {
        this.apiIntegrator = apiIntegrator;
    }

    /**
     * Nos retorna el estado en el que se encuentra el integrador
     *
     * @return Boolean
     * @see MessageIntegrator
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Integrator{" +
                "idIntegrator=" + get_id() +
                ", threadCapacity=" + threadCapacity +
                ", messageCost=" + messageCost +
                ", nameIntegrator='" + nameIntegrator + '\'' +
                ", apiIntegrator='" + apiIntegrator + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
