package Entities.M08_Validation.XMLManagement;

public class ParameterXML {
    private String _name;
    private String _value;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }

    @Override
    public String toString() {
        return "ParameterXML{" +
                "_name='" + _name + '\'' +
                ", _value='" + _value + '\'' +
                '}';
    }
}