package Classes.M07_Template.MessagePackage;

import java.util.*;

public class Parameter {
    private int parameterId;
    private String name;
    private String description;

    public Parameter() {}

    public Parameter (String name){
        this.name = name;
    }

    public Parameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Parameter(int parameterId, String name) {
        this.parameterId = parameterId;
        this.name = name;
    }

    public Parameter(int parameterId, String name, String description) {
        this.parameterId = parameterId;
        this.name = name;
        this.description = description;
    }

    public int getParameterId() { return parameterId; }

    public void setParameterId(int parameterId) { this.parameterId = parameterId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

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

}

