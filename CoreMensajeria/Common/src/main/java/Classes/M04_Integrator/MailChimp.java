package Classes.M04_Integrator;

public class MailChimp extends Integrator {

    public MailChimp(int idIntegrator, int threadCapacity, float messageCost, String nameIntegrator, String apiIntegrator, boolean enabled) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator,enabled);
    }

    @Override
    public void testConection() {

    }

    @Override
    public void sendMessage() {

    }
}
