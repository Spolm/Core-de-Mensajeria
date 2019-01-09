package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.Template;

import java.util.ArrayList;

public class VerifiedParameter {
    private ArrayList<Message> _verifiedMessages;
    private Template _template;

    public VerifiedParameter(ArrayList<Message> _verifiedMessages, Template _template) {
        this._verifiedMessages = _verifiedMessages;
        this._template = _template;
    }

    public ArrayList<Message> get_verifiedMessages() {
        return _verifiedMessages;
    }

    public void set_verifiedMessages(ArrayList<Message> _verifiedMessages) {
        this._verifiedMessages = _verifiedMessages;
    }

    public Template get_template() {
        return _template;
    }

    public void set_template(Template _template) {
        this._template = _template;
    }
}
