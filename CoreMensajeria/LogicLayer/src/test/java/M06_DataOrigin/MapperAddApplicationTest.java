package M06_DataOrigin;

import DTO.DTOFactory;
import DTO.M06_DataOrigin.DTOAddApplication;
import Entities.Entity;
import Entities.Sql;
import Exceptions.DatabaseConnectionProblemException;
import Logic.Command;
import Logic.CommandsFactory;
import Mappers.M06_DataOrigin.MapperDTOAddApplication;
import Mappers.MapperFactory;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapperAddApplicationTest {

    private static DTOAddApplication addApplication;

    @BeforeEach
    private void before(){

    //    addApplication = DTOFactory.addApplication("Metro de Caracas",
      //          "Pagina oficial del metro de caracas",1,1);
    }

    @Test
    public void createApplicationsMapperTest() throws Exception{

        try {
            addApplication = DTOFactory.addApplication("Metro de Caracas",
                    "Pagina oficial del metro de caracas",1,1);
            MapperDTOAddApplication _mapper = MapperFactory.mapperDTOAddAplication();
            Entity _addApplication = _mapper.CreateEntity(addApplication);
            Command c = CommandsFactory.CreateApplication(_addApplication);
            c.execute();
            assertNotNull(c.Return());
        } catch (DatabaseConnectionProblemException e){

            e.printStackTrace();
        }
    }

    @AfterEach
    public void TearDownClass() throws SQLException {

        Connection conn;
        conn = Sql.getConInstance();
        String query = "DELETE FROM public.application WHERE app_name = 'Metro de Caracas';";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }

}

