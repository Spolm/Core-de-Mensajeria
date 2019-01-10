package Persistence.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Persistence.IDAO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IDAOCompany extends IDAO {

    Entity company( Entity e ) throws SQLException;

    void changeStatus ( Entity e);

    ArrayList<Entity> companiesByResponsible( Entity e );

    Company companyById( Company e);

    ArrayList<Entity> companiesByUser( Company e );

    ArrayList<Entity> companiesEnabled();

    ArrayList<Company> allCompanies();


}


