package Entities.M04_Integrator;

import java.util.Objects;

/**
* Clase que nos permite realizar el envío de mensajes
* por integrador
*
* @author Kevin Martinez
* @author Braulio Picon
* @author Alexander Fernandez
*/

public class MessageIntegrator {
    private String msg;
    private String address;
    private String acknowledge;
    private boolean send;

    /**
     * Constructor de la clase MessageIntegrator
     *
     * @param msg     Mensaje que se esta enviando
     * @param address Receptor del mensaje
     * @param idMsg   id por mensaje
     */

    public MessageIntegrator(String msg, String address, String idMsg) {
        this.msg = msg;
        this.address = address;
        this.acknowledge = idMsg;
        this.send = false;
    }

    public boolean isSend() {
        return send;
    }

    public void send() {
        this.send = true;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(String acknowledge) {
        this.acknowledge = acknowledge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        MessageIntegrator that = (MessageIntegrator) o;
        return send == that.send &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(address, that.address) &&
                Objects.equals(acknowledge, that.acknowledge);
    }
}
