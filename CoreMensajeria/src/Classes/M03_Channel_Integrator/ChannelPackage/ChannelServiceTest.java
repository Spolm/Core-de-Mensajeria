package Classes.M03_Channel_Integrator.ChannelPackage;

import Classes.M03_Channel_Integrator.IntegratorPackage.Integrator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChannelServiceTest {
    static ChannelService channelService;

    @Test
    public void TestIntegradorsByChannel(){
        ArrayList<Integrator> i = channelService.getInstance().listIntegratorByChannel(1);
        assertNotNull(i);
    }

    @Test
    public void TestConcreteIntegrator(){

    }
}