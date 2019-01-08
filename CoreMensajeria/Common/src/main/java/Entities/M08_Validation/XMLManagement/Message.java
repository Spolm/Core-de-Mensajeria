package Entities.M08_Validation.XMLManagement;

import java.util.ArrayList;

public class Message {

    private ArrayList<ParameterXML> _param;
    private String _correo;
    private String _telefono;

    public ArrayList<ParameterXML> get_param() {
        return _param;
    }

    public void set_param(ArrayList<ParameterXML> _param) {
        this._param = _param;
    }

    public String get_correo() {
        return _correo;
    }

    public void set_correo(String _correo) {
        this._correo = _correo;
    }

    public String get_telefono() {
        return _telefono;
    }

    public void set_telefono(String _telefono) {
        this._telefono = _telefono;
    }

    @Override
    public String toString() {
        return "Message{" +
                "_param=" + _param.toString() +
                ", _correo='" + _correo + '\'' +
                ", _telefono='" + _telefono + '\'' +
                '}';
    }
}
