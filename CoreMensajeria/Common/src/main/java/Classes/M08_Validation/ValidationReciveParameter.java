package Classes.M08_Validation;
import Classes.M08_Validation.ValidationData;
import java.util.List;

public class ValidationReciveParameter {


    private int template_id;
    private List<String> data;

    //get
    public int getTemplate_id() {
        return template_id;
    }

    public List<String> getData() {
        return data;
    }


    //Set
    public void setData(List<String> data) {
        this.data = data;
    }


    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
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
    public ValidationReciveParameter(int template_id, List<String> data) {
        this.template_id = template_id;
        this.data = data;
    }

}
