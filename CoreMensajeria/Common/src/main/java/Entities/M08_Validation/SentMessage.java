package Entities.M08_Validation;

import Entities.Entity;

import java.sql.Timestamp;


public class SentMessage extends Entity {
    public int _templateId;
    public String _message;
    public int _campaignId;
    public int _integratorId;
    public int _applicationId;
    public String _channel;
    public Timestamp _sentTime;

    public int get_templateId() {
        return _templateId;
    }

    public void set_templateId(int _templateId) {
        this._templateId = _templateId;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
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

    public String get_channel() {
        return _channel;
    }

    public void set_channel(String _channel) {
        this._channel = _channel;
    }

    public Timestamp get_sentTime() {
        return _sentTime;
    }

    public void set_sentTime(Timestamp _sentTime) {
        this._sentTime = _sentTime;
    }
}
