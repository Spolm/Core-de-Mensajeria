package Entities.M08_Validation;
import java.util.List;

public class ValidationReciveParameter {


    public class Parameter {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        String name;
        String value;
    }
    public class Data {
        public Parameter getParameter() {
            return parameter;
        }

        public void setParameter(Parameter parameter) {
            this.parameter = parameter;
        }

        Parameter parameter;

    }

    private int template_id;
    private List<Data> data;

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }



    //CONSTRUCTOR

    /**
     * Constructor Vacio
     */

    public ValidationReciveParameter() {
    }

    /*** Construtor de para Validacion de parametros de envio completos
     * @param template_id
     * @param data
     * */
    public ValidationReciveParameter(int template_id, List<Data> data) {
        this.template_id = template_id;
        this.data = data;
    }

}
