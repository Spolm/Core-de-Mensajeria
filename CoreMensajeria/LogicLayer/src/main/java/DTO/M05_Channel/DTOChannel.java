package DTO.M05_Channel;

import DTO.DTO;
import Entities.Entity;

import java.util.ArrayList;

public class DTOChannel extends DTO {

    private String _nameChannel;
    private String _descriptionChannel;
    private ArrayList<Entity> _integrators;

    public DTOChannel(String nameChannel, String descriptionChannel, ArrayList<Entity> integrators) {
        this._nameChannel = nameChannel;
        this._descriptionChannel = descriptionChannel;
        this._integrators = integrators;
    }

    public String get_nameChannel() {
        return _nameChannel;
    }

    public void set_nameChannel(String nameChannel) {
        this._nameChannel = nameChannel;
    }

    public String get_descriptionChannel() {
        return _descriptionChannel;
    }

    public void set_descriptionChannel(String descriptionChannel) {
        this._descriptionChannel = descriptionChannel;
    }

    public ArrayList<Entity> get_integrators() {
        return _integrators;
    }

    public void set_integrators(ArrayList<Entity> integrators) {
        this._integrators = integrators;
    }
}
