package Classes.M04_Integrator;

public interface IIntegrator {
    void testConection();

    MessageIntegrator sendMessage(String msg, String address, String idMsg);
}
