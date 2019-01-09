package M02_CompanyManagement;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOFullCompany;
import DTO.M02_DTO.DTOIdStatusCompany;
import Entities.M02_Company.Company;
import Exceptions.CompanyDoesntExistsException;


import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import webService.M02_CompanyManagement.M02_Companies;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class M02_CompaniesTest {

    private ArrayList<Company> _coList;
    private Company _co;



    /**
     * Pruebas unitarias para el Metodo getAllCompaniesPP en web service M02_Companies
     *
     * */
    @Test
    void  getAllCompaniesPPTest() throws CompanyDoesntExistsException{
        try {
            M02_Companies intance = new M02_Companies ();
            Response salida = intance.getAllCompaniesPP();
            assertEquals(200, salida.getStatus());
            assertNotNull( salida.getEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }

    }

    /**
     * Pruebas unitarias para el Metodo getCompaniesByUser en web service M02_Companies
     *
     * */
    @Test
    void getCompaniesByUserTest() throws CompanyDoesntExistsException {
        try{
            M02_Companies intance = new M02_Companies ();
            Response salida = intance.getCompaniesByUserPP (1) ;
            assertEquals(200, salida.getStatus());
            assertNotNull(salida);
        }catch (Exception e){
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }
    }

    @Test
    void f() {
        try {

        } catch (Exception e) {

        }


    }
}
