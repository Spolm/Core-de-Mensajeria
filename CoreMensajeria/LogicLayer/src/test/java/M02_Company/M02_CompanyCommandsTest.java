package M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Entities.M05_Channel.Channel;
import Entities.M09_Statistics.Statistics;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M02_Company.GetAllCompaniesCommand;
import Logic.M02_Company.GetCompanyByUserCommand;
import Logic.M02_Company.GetCompanyCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class M02_CompanyCommandsTest {

    @BeforeEach
    void setUp(){}

    @AfterEach
    void tearDown() {
    }

    /**
     * Pruebas unitarias para el Metodo GetAllCompaniesCommand en LogicLayer/Logic/M02_Company
     *
     * */
    @Test
    void GetAllCompaniesCommandTest(){
        Command<ArrayList<Company>> command;
        command = CommandsFactory.createGetAllCompaniesCommand();
        ArrayList<Entity> _companies;
        try {
            command.execute();
            _companies = ((GetAllCompaniesCommand) command).ReturnList();
            assertNotNull( _companies );
            assertEquals( _companies.size() , 8);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Pruebas unitarias para el Metodo GetCompanyByUserCommand en LogicLayer/Logic/M02_Company
     *
     * */
    @Test
    void GetCompanyByUserCommandTest(){
        Company _co = new Company(1);
        Command<ArrayList<Company>> command;
        command = CommandsFactory.createGetCompanyByUserCommand( _co );
        List<Entity> _companies;

        try {
            command.execute();
            _companies = ((GetCompanyByUserCommand) command).ReturnList();
            assertNotNull( _companies );
            assertEquals( _companies.size() , 8);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Pruebas unitarias para el Metodo GetCompanyCommand en LogicLayer/Logic/M02_Company
     *
     * */
    @Test
    void GetCompanyCommandTest(){
        Company _co = new Company(1);
        Command<ArrayList<Company>> command;
        command = CommandsFactory.createGetCompanyCommand( _co );
        Company _companies ;

        try {
            command.execute();
            _companies = ((GetCompanyCommand) command).Return();
            assertNotNull( _companies );
            assertEquals( _companies.get_idCompany() , 1 );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
