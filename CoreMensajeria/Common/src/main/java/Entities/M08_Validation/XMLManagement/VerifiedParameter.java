package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.Template;

import java.util.ArrayList;


/**
 * Clase que contiene los mensajes verificados junto a la plantilla
 * con la cual se quiere realizar el envio del mensaje
 *
 * @see Message
 * @see Template
 */

public class VerifiedParameter {
    /**
     * Lista de mensajes verificados
     */
    private ArrayList<Message> _verifiedMessages;

    /**
     * Plantilla con la cual se quiere enviar el mensaje
     */
    private Template _template;

    /**
     * Constructor con la lista de los Mensajes verificados y la plantilla
     * @see Message
     * @see Template
     */
    public VerifiedParameter( ArrayList<Message> _verifiedMessages, Template _template ) {
        this._verifiedMessages = _verifiedMessages;
        this._template = _template;
    }

    /**
     * Metodo que nos permite obtener la lista de los mensajes verificados
     * @return _verifiedMessages lista de los mensajes verificados
     */
    public ArrayList<Message> get_verifiedMessages() {
        return _verifiedMessages;
    }

    /**
     * Metodo que nos permite asignar lista de mensajes verificados
     * @param _verifiedMessages lista de mensajes verificados
     */
    public void set_verifiedMessages( ArrayList<Message> _verifiedMessages ) {
        this._verifiedMessages = _verifiedMessages;
    }

    /**
     * Metodo que nos permite obter la plantilla con la cual
     * se realizara el envio del mensaje
     * @return _template plantilla a la
     */
    public Template get_template() {
        return _template;
    }

    /**
     * Metodo que nos permite asignar la plantilla con la cual
     * se realizara el envio del mensaje
     * @param _template plantilla con la cual se quiere enviar el mensaje
     */
    public void set_template( Template _template ) {
        this._template = _template;
    }
}
