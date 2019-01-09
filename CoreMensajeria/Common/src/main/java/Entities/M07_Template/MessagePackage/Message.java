package Entities.M07_Template.MessagePackage;

import Entities.Entity;

import java.util.*;

/**
 * La clase Message se usa para almacenar informacion del mensaje de una plantilla.
 */
public class Message extends Entity {
    /**
     * Lista de parametros de un mensaje
     */
    private ArrayList<Parameter> parameterArrayList;
    /**
     * String de palabras del mensaje
     */
    private String message;

    private int templateId;

    /**
     * Constructor vacio
     */
    public Message() {}

    /**
     * Contructor con solo el id del mensaje
     * @param messageId message id
     */
    public Message(int messageId) {
        this.set_id(messageId);
    }

    /**
     * Constructor con la lsita de parametros y un String con mensaje y parametros.
     * @param parameterArrayList list of parameters of the message
     * @param message string with message and parameters
     */
    public Message(ArrayList<Parameter> parameterArrayList, String message) {
        this.parameterArrayList = parameterArrayList;
        this.message = message;
    }

    /**
     * Constructor con el id del mensaje, la lista de parametros y un String con mensaje y parametros.
     * @param messageId message id
     * @param parameterArrayList lista de parametros del mensaje
     * @param message string con mensaje y parametros
     */
    public Message(int messageId, ArrayList<Parameter> parameterArrayList, String message) {
        this.set_id(messageId);
        this.parameterArrayList = parameterArrayList;
        this.message = message;
    }

    public Message(int messageId, String message, int templateId) {
        this.set_id(messageId);
        this.message = message;
        this.templateId = templateId;
    }

    /**
     * Constructor con toda la data.
     * @param messageId
     * @param parameterArrayList
     * @param message
     * @param templateId
     */
    public Message(int messageId,ArrayList<Parameter> parameterArrayList, String message, int templateId) {
        this.set_id(messageId);
        this.parameterArrayList = parameterArrayList;
        this.message = message;
        this.templateId = templateId;
    }

    /**
     * Muestra la lista de parametros del mensaje
     * @return ArrayList of parameters
     */
    public ArrayList<Parameter> getParameterArrayList() {
        return parameterArrayList;
    }

    /**
     * Anadir/modificar la lista de parametros del mensaje
     * @param parameters new list of the parameters
     */
    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameterArrayList = parameters;
    }

    public void addParameter(Parameter parameter){
        parameterArrayList.add(parameter);
    }

    /**
     * Muestra el string con mensaje y parametros del mensaje.
     * @return string whit message and parameters of the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Anadir/modificar el string con mensaje y parametros del mensaje.
     * @param message new string whit message and parameters
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtener el id de la plantilla.
     * @return Template ID
     */
    public int getTemplateId() {
        return templateId;
    }

    /**
     * Poner el id de la plantilla
     * @param templateId
     */
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

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

