package Entities.M08_Validation.XMLManagement;

import java.util.ArrayList;

public class Message {
    private ArrayList<Parameter> _param;
    private String _destiny;

    public ArrayList<Parameter> get_param() {
        return _param;
    }

    public void set_param(ArrayList<Parameter> _param) {
        this._param = _param;
    }

    public String get_destiny() {
        return _destiny;
    }

    public void set_destiny(String _destiny) {
        this._destiny = _destiny;
    }

    @Override
    public String toString() {
        return "Entities.M08_Validation.XMLManagement.Message{" +
                "_param=" + _param.toString() +
                ", _destiny='" + _destiny + '\'' +
                '}';
    }
}
