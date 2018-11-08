package Classes.M04_Channel_Integrator.ChannelPackage;
import Classes.M04_Channel_Integrator.IntegratorPackage.Integrator;
import java.util.ArrayList;

public abstract class Channel {
    private int idChannel;
    private String nameChannel;
    private String descriptionChannel;
    ArrayList<Integrator> integrators;

    public Channel(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        this.idChannel = idChannel;
        this.nameChannel = channelName;
        this.descriptionChannel = channelDescription;
        this.integrators = integrators;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getDescriptionChannel() {
        return descriptionChannel;
    }

    public void setDescriptionChannel(String descriptionChannel) {
        this.descriptionChannel = descriptionChannel;
    }

    public ArrayList<Integrator> getIntegrators() {
        return integrators;
    }

    public void setIntegrators(ArrayList<Integrator> integrators) {
        this.integrators = integrators;
    }

}
