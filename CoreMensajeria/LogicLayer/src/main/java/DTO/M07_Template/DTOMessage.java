package DTO.M07_Template;

import DTO.DTO;
import Entities.M07_Template.MessagePackage.Parameter;

import java.util.ArrayList;

public class DTOMessage extends DTO {

    private int _mMessageId;
    private ArrayList<Parameter> _mParameterArrayList;
    private String _mMessage;

    public DTOMessage() {
    }

    public DTOMessage(int _mMessageId, ArrayList<Parameter> _mParameterArrayList, String _mMessage) {
        this._mMessageId = _mMessageId;
        this._mParameterArrayList = _mParameterArrayList;
        this._mMessage = _mMessage;
    }

    public int get_mMessageId() {
        return _mMessageId;
    }

    public void set_mMessageId(int _mMessageId) {
        this._mMessageId = _mMessageId;
    }

    public ArrayList<Parameter> get_mParameterArrayList() {
        return _mParameterArrayList;
    }

    public void set_mParameterArrayList(ArrayList<Parameter> _mParameterArrayList) {
        this._mParameterArrayList = _mParameterArrayList;
    }

    public String get_mMessage() {
        return _mMessage;
    }

    public void set_mMessage(String _mMessage) {
        this._mMessage = _mMessage;
    }
}
