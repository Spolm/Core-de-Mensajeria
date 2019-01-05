package Entities.M08_Validation;

import Entities.Entity;


public class SentMessage extends Entity {
    public int _templateId;
    public int _messageId;
    public int _campaignId;
    public int _integratorId;
    public int _applicationId;
    public int _channelId;

    public int get_templateId() {
        return _templateId;
    }

    public void set_templateId(int _templateId) {
        this._templateId = _templateId;
    }

    public int get_messageId() {
        return _messageId;
    }

    public void set_messageId(int _messageId) {
        this._messageId = _messageId;
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

    public int get_channelId() {
        return _channelId;
    }

    public void set_channelId(int _channelId) {
        this._channelId = _channelId;
    }

}
