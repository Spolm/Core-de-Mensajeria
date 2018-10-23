package Modulo_3;

import java.util.ArrayList;

public abstract class Channel {
    private int idCanal;
    private String channelName;
    private String channelDescription;
    private ArrayList<Integrator> integrators;

    public Channel() {
    }

    public Channel(int idCanal, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        this.idCanal = idCanal;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.integrators = integrators;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public ArrayList<Integrator> getIntegrators() {
        return integrators;
    }

    public void setIntegrators(ArrayList<Integrator> integrators) {
        this.integrators = integrators;
    }
}
