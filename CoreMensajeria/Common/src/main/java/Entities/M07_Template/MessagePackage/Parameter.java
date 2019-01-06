package Entities.M07_Template.MessagePackage;

import java.util.*;

/**
 * Message class uses for storing message information from a message
 */
public class Parameter {
    /**
     * id of the parameter
     */
    private int parameterId;
    /**
     * name of the parameter
     */
    private String name;
    /**
     * description of the parameter
     */
    private String description;

    /**
     * empty builder
     */
    public Parameter() {}

    /**
     * builder with only name
     * @param name name of the parameter
     */
    public Parameter (String name){
        this.name = name;
    }

    /**
     * builder with name and description
     * @param name  name of the parameter
     * @param description description of the parameter
     */
    public Parameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * builder with name and id of the parameter
     * @param parameterId id of the parameter
     * @param name name of the parameter
     */
    public Parameter(int parameterId, String name) {
        this.parameterId = parameterId;
        this.name = name;
    }

    /**
     * buider with name, description and id of the parameter
     * @param parameterId id of the parameter
     * @param name name of the parameter
     * @param description description of the parameter
     */
    public Parameter(int parameterId, String name, String description) {
        this.parameterId = parameterId;
        this.name = name;
        this.description = description;
    }

    /**
     * show id of the parameter
     * @return id of the parameter
     */
    public int getParameterId() { return parameterId; }

    /**
     * add the id of the parameter
     * @param parameterId new id of the parameter
     */
    public void setParameterId(int parameterId) { this.parameterId = parameterId; }

    /**
     * show the name of the parameter
     * @return  name of the parameter
     */
    public String getName() { return name; }

    /**
     * add/modify the name of the parameter
     * @param name  new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * show the description of the parameter
     * @return  descrption of the parameter
     */
    public String getDescription() { return description; }

    /**
     * add/modify description of the parameter
     * @param description new description
     */
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(getName(), parameter.getName()) &&
                Objects.equals(getDescription(), parameter.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                '}';
    }
}

