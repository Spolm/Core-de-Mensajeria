package Entities.M08_Validation.XMLManagement;

import java.util.ArrayList;

public class Message {
    private ArrayList<ParameterXML> _param;
    private String _destiny;

    public ArrayList<ParameterXML> get_param() {
        return _param;
    }

    public void set_param(ArrayList<ParameterXML> _param) {
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
