package Classes.M04_Integrator;

public class Message {
    private String msg;
    private String address;
    private String acknowledge;
    private boolean send;

    public Message (String msg, String address, String idMsg) {
        this.msg = msg;
        this.address = address;
        this.acknowledge = idMsg;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
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
}
