package Classes.M04_Channel_Integrator.IntegratorPackage;

public abstract class Integrator implements IIntegrator {
    private int idIntegrator;
    private int threadCapacity;
    private float messageCost;
    private String nameIntegrator;
    private String apiIntegrator;

    public Integrator(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator) {
        this.idIntegrator = idIntegrator;
        this.threadCapacity = threadCapacity;
        this.messageCost = messageCost;
        this.nameIntegrator = nameIntegrator;
        this.apiIntegrator = apiIntegrator;
    }

    public int getIdIntegrator() {
        return idIntegrator;
    }

    public void setIdIntegrator(int idIntegrator) {
        this.idIntegrator = idIntegrator;
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

    @Override
    public String toString() {
        return "Integrator{" +
                "idIntegrator=" + idIntegrator +
                ", threadCapacity=" + threadCapacity +
                ", messageCost=" + messageCost +
                ", nameIntegrator='" + nameIntegrator + '\'' +
                ", apiIntegrator='" + apiIntegrator + '\'' +
                '}';
    }
}
