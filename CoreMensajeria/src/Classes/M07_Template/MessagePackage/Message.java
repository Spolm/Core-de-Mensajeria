package Classes.M07_Template.MessagePackage;

import Classes.M07_Template.MessagePackage.Parameter;
import java.util.ArrayList;

public class Message {
    private ArrayList<Parameter> parameters;
    private String message;

    public Message() {
    }

    public Message(ArrayList<Parameter> parameters, String message) {
        this.parameters = parameters;
        this.message = message;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getMessage() {
        return message;
    }

    public void set_message(String message) {
        this.message = message;
    }
}

