package Entities.M07_Template.MessagePackage;

import Entities.Entity;

import java.util.*;

/**
 * La clase Parametros tiene la informacion de los parametros que pueden variar en un mensaje.
 */
public class Parameter extends Entity {
    /**
     * id del parametro
     */
    private int parameterId;
    /**
     * nombre del parameter
     */
    private String name;
    /**
     * descripcion del parameter
     */
    private String description;

    /**
     * Company ID
     */
    private int companyId;

    /**
     * Constructor vacio
     */
    public Parameter() {}

    /**
     * Constructor con solo el nombre
     * @param name nombre del parametro
     */
    public Parameter (String name){
        this.name = name;
    }

    /**
     * Constructor con nombre y descripcion
     * @param name  nombre del parametro
     * @param description descripcion del parametro
     */
    public Parameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor con nombre y id del parametro
     * @param parameterId id del parameter
     * @param name nombre del parametro
     */
    public Parameter(int parameterId, String name) {
        this.parameterId = parameterId;
        this.name = name;
    }

    /**
     * Constructor con nombre, descripcion e id del parametro.
     * @param parameterId id del parametro
     * @param name nombre del parametro
     * @param description descripcion del parametro
     */
    public Parameter(int parameterId, String name, String description) {
        this.parameterId = parameterId;
        this.name = name;
        this.description = description;
    }

    public Parameter(int parameterId, String name, String description, int companyId) {
        this.parameterId = parameterId;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
    }

    /**
     * Muestra id del parametro
     * @return id of the parameter
     */
    public int getParameterId() { return parameterId; }

    /**
     * Anade el id del parametro
     * @param parameterId new id of the parameter
     */
    public void setParameterId(int parameterId) { this.parameterId = parameterId; }

    /**
     * Muestra el nombre del parametro
     * @return  name of the parameter
     */
    public String getName() { return name; }

    /**
     * Anade/modifica el nombre del parametro
     * @param name  new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Muestra la descripcion del parametro
     * @return  descripcion del parametro
     */
    public String getDescription() { return description; }

    /**
     * Anade/modifica la descripcion del parametro
     * @param description new description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Muestra el id de la compania
     * @return
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Anade/modifica el id de la compania
     * @param companyId
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

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

