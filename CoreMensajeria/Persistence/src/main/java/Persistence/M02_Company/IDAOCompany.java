package Persistence.M02_Company;

import Entities.Entity;
import Entities.M02_Company.Company;
import Persistence.IDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IDAOCompany extends IDAO {

    Entity company( Entity e ) throws SQLException;

    void changeStatus ( Entity e);

    ArrayList<Entity> companiesByResponsible( Entity e );

    Entity companyById( Entity e);

    ArrayList<Entity> companiesByUser( Entity e );

    ArrayList<Entity> companiesEnabled();

    ArrayList<Entity> allCompanies();


}


