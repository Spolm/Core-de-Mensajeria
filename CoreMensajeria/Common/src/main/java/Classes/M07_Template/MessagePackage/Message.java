package Classes.M07_Template.MessagePackage;

import Classes.M07_Template.MessagePackage.Parameter;

import java.util.*;

public class Message {
    private int messageId;
    private ArrayList<Parameter> parameterArrayList;
    private String message;

    public Message() {}

    public Message(int messageId) {
        this.messageId = messageId;
    }

    public Message(ArrayList<Parameter> parameterArrayList, String message) {
        this.parameterArrayList = parameterArrayList;
        this.message = message;
    }

    public Message(int messageId, ArrayList<Parameter> parameterArrayList, String message) {
        this.messageId = messageId;
        this.parameterArrayList = parameterArrayList;
        this.message = message;
    }

    public ArrayList<Parameter> getParameterArrayList() {
        return parameterArrayList;
    }
    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameterArrayList = parameters;
    }

    public void addParameter(Parameter parameter){
        parameterArrayList.add(parameter);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageId() { return messageId; }

    public void setMessageId(int messageId) { this.messageId = messageId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return Objects.equals(getParameterArrayList(), message1.getParameterArrayList()) &&
                Objects.equals(getMessage(), message1.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParameterArrayList(), getMessage());
    }

}

