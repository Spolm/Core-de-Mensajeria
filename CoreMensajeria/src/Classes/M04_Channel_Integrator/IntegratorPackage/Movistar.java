package Classes.M04_Channel_Integrator.IntegratorPackage;

public class Movistar extends Integrator {

    public Movistar(int idIntegrator, int threadCapacity, float messageCost,
                    String nameIntegrator, String apiIntegrator) {
        super(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);
    }

    @Override
    public void testConection() {}

    @Override
    public void sendMessage() {}
}
