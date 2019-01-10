package DTO.M04_Integrator;

import DTO.DTO;

public class DTOIntegrator extends DTO {
    private int id;
    private int threadCapacity;
    private float messageCost;
    private boolean enabled;
    private String integratorType;
    private String nameIntegrator;
    private String apiIntegrator;

    /**
    * @param id
    * @param integratorType
    * @param threadCapacity
    * @param messageCost
    * @param nameIntegrator
    * @param apiIntegrator
    * @param enabled
     * @see DTOIntegrator
    */
    public DTOIntegrator(int id, String integratorType, int threadCapacity, float messageCost, String nameIntegrator,
                         String apiIntegrator, boolean enabled) {
        this.id = id;
        this.integratorType = integratorType;
        this.threadCapacity = threadCapacity;
        this.messageCost = messageCost;
        this.nameIntegrator = nameIntegrator;
        this.apiIntegrator = apiIntegrator;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIntegratorType() {
        return integratorType;
    }

    public void setIntegratorType(String integratorType) {
        this.integratorType = integratorType;
    }
}
