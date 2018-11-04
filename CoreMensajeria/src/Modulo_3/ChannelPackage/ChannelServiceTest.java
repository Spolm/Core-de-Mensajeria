package Modulo_3.ChannelPackage;

import Modulo_3.IntegratorPackage.Integrator;
import org.junit.jupiter.api.BeforeAll;
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