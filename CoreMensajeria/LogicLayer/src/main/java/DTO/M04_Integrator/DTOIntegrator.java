package DTO.M04_Integrator;

import DTO.DTO;

public class DTOIntegrator extends DTO {

    private int threadCapacity;
    private float messageCost;
    private String nameIntegrator;
    private String apiIntegrator;
    private boolean enabled;

    /**
     *

     * @param threadCapacity
     * @param messageCost
     * @param nameIntegrator
     * @param apiIntegrator
     * @param enabled
     */
    public DTOIntegrator(int threadCapacity, float messageCost, String nameIntegrator,
                         String apiIntegrator, boolean enabled) {

        this.threadCapacity = threadCapacity;
        this.messageCost = messageCost;
        this.nameIntegrator = nameIntegrator;
        this.apiIntegrator = apiIntegrator;
        this.enabled = enabled;
    }

    /**
     *
     */
    public DTOIntegrator() {

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
