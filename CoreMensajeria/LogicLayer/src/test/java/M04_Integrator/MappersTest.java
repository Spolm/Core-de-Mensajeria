package M04_Integrator;

import DTO.M04_Integrator.DTOIntegrator;
import Entities.Entity;
import Entities.M04_Integrator.Integrator;
import Entities.M04_Integrator.Movistar;
import Mappers.M04_Integrator.MapperIntegrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertNotNull;

public class MappersTest {
    private static MapperIntegrator _mapper;
    private static Integrator _integrator;
    private static DTOIntegrator _dtoIntegrator;

    @BeforeEach
    public void init() {
        _mapper = new MapperIntegrator();
        _integrator = new Movistar(1,25,13.4f,"Movistar",
                "oqiwueyeiu", true);
        _dtoIntegrator = new DTOIntegrator("MOVISTAR",25, 13.4f, "Movistar",
                "oqiwueyeiu", true);
    }

    @Test
    public void CreateDtoTest(){

        DTOIntegrator dtoIntegrator = _mapper.CreateDto(_integrator);
        assertNotNull(dtoIntegrator);
        assertEquals("Movistar",dtoIntegrator.getNameIntegrator());
        assertEquals(25,dtoIntegrator.getThreadCapacity());
        assertEquals("oqiwueyeiu",dtoIntegrator.getApiIntegrator());
        assertTrue(dtoIntegrator.isEnabled());
    }

    @Test
    public void CreateEntityTest(){
        Integrator movistar = (Integrator) _mapper.CreateEntity(_dtoIntegrator);
        assertNotNull(movistar);
        assertEquals(-1, movistar.get_id());
        assertEquals("Movistar", movistar.getNameIntegrator());
        assertEquals("oqiwueyeiu", movistar.getApiIntegrator());
        assertEquals(25, movistar.getThreadCapacity());
        assertTrue(movistar.isEnabled());
    }

    @Test
    public void CreateEntityListTest(){
        ArrayList<DTOIntegrator> dtoIntegratorList = new ArrayList<>();
        dtoIntegratorList.add(_dtoIntegrator);
        assertNotNull(_mapper.CreateEntityList(dtoIntegratorList));
    }

    @Test
    public void CreateDTOListTest(){
        ArrayList<Entity> integratorList = new ArrayList<>();
        integratorList.add(_integrator);
        assertNotNull(_mapper.CreateDtoList(integratorList));
    }
}
