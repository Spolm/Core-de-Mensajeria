package DTO.M08_DTO;

import DTO.DTO;
import Entities.M08_Validation.XMLManagement.Message;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class ParametersDTO extends DTO {
    @NotNull
    private int _idTemplate;
    private ArrayList<Message> _verifiedMessages;

    public int get_idTemplate() {
        return _idTemplate;
    }

    public void set_idTemplate(int _idTemplate) {
        this._idTemplate = _idTemplate;
    }

    public ArrayList<Message> get_verifiedMessages() {
        return _verifiedMessages;
    }

    public void set_verifiedMessages(ArrayList<Message> _verifiedMessages) {
        this._verifiedMessages = _verifiedMessages;
    }
}
