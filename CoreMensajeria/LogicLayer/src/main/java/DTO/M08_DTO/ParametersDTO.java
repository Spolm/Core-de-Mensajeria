package DTO.M08_DTO;

import DTO.DTO;
import Entities.M08_Validation.XMLManagement.Message;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Objeto de transferencia de datos que contiene
 * el id de la plantilla y la lista de todos los mensajes verificados
 *
 * @see DTO
 * @see Message
 */
public class ParametersDTO extends DTO {
    /**
     * Id de la plantilla
     */
    @NotNull
    private int _idTemplate;

    /**
     * Lista de mensajes verificados
     */
    private ArrayList<Message> _verifiedMessages;

    /**
     * Metodo que permite obtener el id de la plantilla
     *
     * @return _idTemplate  id de la plantilla
     */
    public int get_idTemplate() {
        return _idTemplate;
    }

    /**
     * Metodo que permite asignar el id de la plantilla
     *
     * @param _idTemplate id de la plantilla
     */
    public void set_idTemplate(int _idTemplate) {
        this._idTemplate = _idTemplate;
    }

    /**
     * Metodo que nos permite obtener la lista de los
     * mensajes verificados
     *
     * @return _verifiedMessages lista de mensajes verificados
     */
    public ArrayList<Message> get_verifiedMessages() {
        return _verifiedMessages;
    }

    /**
     * Metodo que permite asignar la lista de mensajes verificados
     *
     * @param _verifiedMessages lista de mensajes verificados
     */
    public void set_verifiedMessages(ArrayList<Message> _verifiedMessages) {
        this._verifiedMessages = _verifiedMessages;
    }
}
