package Classes.M03_Channel_Integrator.ChannelPackage;
import Classes.M03_Channel_Integrator.IntegratorPackage.Integrator;
import java.util.ArrayList;

public abstract class Channel {
    private int _idChannel;
    private String _nameChannel;
    private String _descriptionChannel;
    ArrayList<Integrator> _integrators;

    public Channel(int _idChannel, String channelName, String channelDescription,
                   ArrayList<Integrator> _integrators) {
        this._idChannel = _idChannel;
        this._nameChannel = channelName;
        this._descriptionChannel = channelDescription;
        this._integrators = _integrators;
    }

    public int get_idChannel() {
        return _idChannel;
    }

    public void set_idChannel(int _idChannel) {
        this._idChannel = _idChannel;
    }

    public String get_nameChannel() {
        return _nameChannel;
    }

    public void set_nameChannel(String _nameChannel) {
        this._nameChannel = _nameChannel;
    }

    public String get_descriptionChannel() {
        return _descriptionChannel;
    }

    public void set_descriptionChannel(String _descriptionChannel) {
        this._descriptionChannel = _descriptionChannel;
    }

    public ArrayList<Integrator> get_integrators() {
        return _integrators;
    }

    public void set_integrators(ArrayList<Integrator> _integrators) {
        this._integrators = _integrators;
    }
}
