package Classes.M04_Integrator;

public class MailChimp extends Integrator {

    public MailChimp(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);
    }

    @Override
    public void testConection() {

    }

    @Override
    public Message sendMessage(String msg, String address, String idMsg) {
        if(this.isEnabled()) {
            try {
                Thread.sleep(2100);
                Message message = new Message(msg, address, idMsg);
                message.setSend(true);
                return message;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            Message message = new Message(msg, address, idMsg);
            message.setSend(false);
            return message;
        }
        return null;
    }
}
