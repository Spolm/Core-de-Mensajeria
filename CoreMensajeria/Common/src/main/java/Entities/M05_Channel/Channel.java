package Entities.M05_Channel;

import Entities.Entity;
import java.util.ArrayList;

/**
 * Clase abstracta Channel que nos proporciona los metodos
 * para trabajar con los canales en concreto.
 *
 * @author Kevin Martinez
 * @author Braulio Picon
 * @author Alexander Fernandez
 */

public abstract class Channel extends Entity {

    private String _nameChannel;
    private String _descriptionChannel;
    ArrayList<Entity> _integrators;

    public Channel(int idChannel, String channelName, String channelDescription, ArrayList<Entity> integrators) {
        this.set_id(idChannel);
        this._nameChannel = channelName;
        this._descriptionChannel = channelDescription;
        this._integrators = integrators;
    }

    public Channel(int idChannel, String channelName, String channelDescription) {
        this.set_id(idChannel);
        this._nameChannel = channelName;
        this._descriptionChannel = channelDescription;
        this._integrators = null;
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

    public ArrayList<Entity> get_integrators() {
        return _integrators;
    }

    public void set_integrators(ArrayList<Entity> integrators) {
        this._integrators = integrators;
    }

}