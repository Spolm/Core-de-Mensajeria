package Entities.M08_Validation;

import Entities.Entity;

import java.sql.Timestamp;


/**
 * Entidad del mensaje enviado correctamente que sera
 * insertado en la base de datos
 *
 * @see Entity
 */
public class SentMessage extends Entity {
    /**
     * Id del mensaje
     */
    private int _messageId;
    /**
     * Id de la campaña
     */
    private int _campaignId;
    /**
     * Id del integrador por el cual se envio el mensaje
     */
    private int _integratorId;
    /**
     * Id de la aplicacion
     */
    private int _applicationId;
    /**
     * Id del canal por el que se envio el mensaje.
     */
    private int _channelId;
    /**
     * Fecha en la cual se enviara el mensaje
     */
    private Timestamp _sentTime;

    /**
     * Metodo que nos permite obtener el id del mensaje
     * que fue enviado
     *
     * @return _messageId id del mensaje
     */
    public int get_message() {
        return _messageId;
    }

    /**
     * Metodo que permite agregar el id a el Mensaje
     *
     * @param _message id del mensaje
     */
    public void set_message(int _message) {
        this._messageId = _message;
    }

    public int get_campaignId() {
        return _campaignId;
    }

    public void set_campaignId(int _campaignId) {
        this._campaignId = _campaignId;
    }

    public int get_integratorId() {
        return _integratorId;
    }

    public void set_integratorId(int _integratorId) {
        this._integratorId = _integratorId;
    }

    public int get_applicationId() {
        return _applicationId;
    }

    public void set_applicationId(int _applicationId) {
        this._applicationId = _applicationId;
    }

    public int get_channel() {
        return _channelId;
    }

    public void set_channel(int _channel) {
        this._channelId = _channel;
    }

    public Timestamp get_sentTime() {
        return _sentTime;
    }

    public void set_sentTime(Timestamp _sentTime) {
        this._sentTime = _sentTime;
    }
}
