package Classes.M04_Integrator;

public class Aweber extends Integrator {

    public Aweber(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator, enabled);
    }

    @Override
    public void testConection() {

    }

    @Override
    public MessageIntegrator sendMessage(String msg, String address, String idMsg) {
        if(this.isEnabled()) {
            try {
                Thread.sleep(1500);
                MessageIntegrator message = new MessageIntegrator(msg, address, idMsg);
                message.setSend(true);
                return message;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            MessageIntegrator message = new MessageIntegrator(msg, address, idMsg);
            message.setSend(false);
            return message;
        }
        return null;
    }
}
