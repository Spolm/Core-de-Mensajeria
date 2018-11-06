package Classes.M07_Template.MessagePackage;

import Classes.M07_Template.MessagePackage.Parameter;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return Objects.equals(getParameters(), message1.getParameters()) &&
                Objects.equals(getMessage(), message1.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParameters(), getMessage());
    }
}

