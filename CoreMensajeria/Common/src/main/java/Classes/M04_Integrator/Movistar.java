package Classes.M04_Integrator;

public class Movistar extends Integrator {

    public Movistar(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);
    }

    @Override
    public void testConection() {

    }

    @Override
    public Message sendMessage(String msg, String address) {
        if(this.isEnabled()) {
            try {
                Thread.sleep(2000);
                Message message = new Message();
                message.setAddress(address);
                message.setMsg(msg);
                message.setAcknowledge("");
                message.setSend(true);
                return message;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            Message message = new Message();
            message.setAddress("");
            message.setMsg("");
            message.setAcknowledge("");
            message.setSend(false);
            return message;
        }
        return null;
    }
}
