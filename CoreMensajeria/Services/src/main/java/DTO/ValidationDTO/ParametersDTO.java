package DTO.ValidationDTO;

import DTO.DTO;
import java.sql.Timestamp;

public class ParametersDTO extends DTO {
    private int _idTemplate;
    private String _name;
    private String _lastName;
    private String _personId;
    private Timestamp _sendTime;
    private String _channel;
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
