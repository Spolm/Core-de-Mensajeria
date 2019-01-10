package Entities.M08_Validation.XMLManagement;

import java.util.ArrayList;

/**
 * Clase Message mensaje que contiene el destino
 * con sus parametros ya validados
 *
 * @see ParameterXML
 */

public class Message {

    /**
     * Lista de parametros con su determinado valor
     * de un Mensaje
     */
    private ArrayList<ParameterXML> _param;

    /**
     * Correo electronico a donde se realizara el
     * envio del mensaje
     */
    private String _correo;

    /**
     * Numero de telefono a donde se realizara el
     * envio del mensaje
     */
    private String _telefono;

    /**
     * Metodo que nos permite obtener la lista de todos los
     * parametros de un determinado mensaje
     *
     * @return _param lista de parametros con su determinado valor
     */
    public ArrayList<ParameterXML> get_param() {
        return _param;
    }

    /**
     * Metodo que nos permite agregar una lista de parametros a
     * un mensaje
     *
     * @param _param lista de parametros de un determinado mensaje.
     */
    public void set_param(ArrayList<ParameterXML> _param) {
        this._param = _param;
    }

    /**
     * Metodo que nos permite obtener el correo al que se va a enviar
     * el mensaje
     *
     * @return _correo direccion a la cual se realizara el envio
     */
    public String get_correo() {
        return _correo;
    }

    /**
     * Metodo que nos permite asignar el correo al que se enviara
     * el mensaje
     *
     * @param _correo correo al que se enviara el mensaje
     */

    public void set_correo(String _correo) {
        this._correo = _correo;
    }


    /**
     * Metodo que nos permite obtener el telefono al que se va a enviar
     * el mensaje
     *
     * @return _telefono telefono al cual se realizara el envio
     */
    public String get_telefono() {
        return _telefono;
    }

    /**
     * Metodo que nos permite asignar el correo al que se enviara
     * el mensaje
     *
     * @param _telefono telefono al que se enviara el mensaje
     */
    public void set_telefono(String _telefono) {
        this._telefono = _telefono;
    }

    /**
     * Metodo que nos permite obtener la informacion
     * almacenada en Message y transformarla a
     * un String
     *
     * @return lista de parametros asiganado a un mensaje
     * con su correo y telefono al que se realizara el envio
     */

    @Override
    public String toString() {
        return "Message{" +
                "_param=" + _param.toString() +
                ", _correo='" + _correo + '\'' +
                ", _telefono='" + _telefono + '\'' +
                '}';
    }
}
