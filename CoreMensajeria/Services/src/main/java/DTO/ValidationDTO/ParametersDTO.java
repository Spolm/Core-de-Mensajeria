package DTO.ValidationDTO;

import DTO.DTO;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ParametersDTO extends DTO {
    @NotNull
    private int _idTemplate;
    @NotNull(message = "_name es requerido")
    private String _name;
    @NotNull(message = "_lastName es requerido")
    private String _lastName;
    @NotNull
    private String _personId;
    private Timestamp _sendTime;
    @NotNull
    private String _channel;
    @NotNull
    private String _message;

    public int get_idTemplate() {
        return _idTemplate;
    }

    public void set_idTemplate(int _idTemplate) {
        this._idTemplate = _idTemplate;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_personId() {
        return _personId;
    }

    public void set_personId(String _personId) {
        this._personId = _personId;
    }

    public Timestamp get_sendTime() {
        return _sendTime;
    }

    public void set_sendTime(Timestamp _sendTime) {
        this._sendTime = _sendTime;
    }

    public String get_channel() {
        return _channel;
    }

    public void set_channel(String _channel) {
        this._channel = _channel;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

}
