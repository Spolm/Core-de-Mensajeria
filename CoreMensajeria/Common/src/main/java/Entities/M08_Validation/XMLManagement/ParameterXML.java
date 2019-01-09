package Entities.M08_Validation.XMLManagement;

/**
 * Clase que nos permite leer la informacion sobre los
 * parametros y su determinado valor que utilizara cada
 * plantilla
 */

public class ParameterXML {
    private String _name;
    private String _value;


    /**
     * Método que nos permite obtener el nombre del
     * parametro
     *
     * @return _name nombre del parametro
     */

    public String get_name() {
        return _name;
    }

    /**
     * Metodo que nos permite colocar el nombre a un parametro
     *
     * @param _name nombre del parametro que utilizara una plantilla
     */


    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Método que nos permite obtener el valor de
     * un determinado parametro
     *
     * @return _value valor de un determinado parametro
     */

    public String get_value() {
        return _value;
    }

    /**
     * Metodo que nos permite asignar el valor a un parametro
     *
     * @param _value valor de un determinado parametro
     */

    public void set_value(String _value) {
        this._value = _value;
    }


    /**
     * Metodo que nos permite obtener la informacion
     * almacenada en ParameterXML y transformarla a
     * un String
     *
     * @return _name  nombre del parametro
     * @return _value valor asignado a determinado parametro
     */

    @Override
    public String toString() {
        return "ParameterXML{" +
                "_name='" + _name + '\'' +
                ", _value='" + _value + '\'' +
                '}';
    }
}
