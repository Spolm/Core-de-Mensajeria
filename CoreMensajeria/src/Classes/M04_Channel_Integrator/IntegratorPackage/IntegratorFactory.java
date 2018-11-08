package Classes.M04_Channel_Integrator.IntegratorPackage;

public class IntegratorFactory {

    public Integrator getIntegrator(String integratorType, int idIntegrator,
                                    String nameIntegrator, float messageCost,
                                    int threadCapacity, String apiIntegrator) {
        if (integratorType == null) {
            return null;
        }
        if (integratorType.equalsIgnoreCase("MOVISTAR")) {
            return new Movistar(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);

        } else if (integratorType.equalsIgnoreCase("DIGITEL")) {
            return new Digitel(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);

        } else if (integratorType.equalsIgnoreCase("MOVILNET")) {
            return new Movilnet(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);

        } else if (integratorType.equalsIgnoreCase("MAILCHIMP")) {
            return new MailChimp(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);

        } else if (integratorType.equalsIgnoreCase("AWEBER")) {
            return new Aweber(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);

        } else if (integratorType.equalsIgnoreCase("INFUSIONSOFT")) {
            return new InfusionSoft(idIntegrator, threadCapacity, messageCost, nameIntegrator, apiIntegrator);
        }

        return null;
    }
}
