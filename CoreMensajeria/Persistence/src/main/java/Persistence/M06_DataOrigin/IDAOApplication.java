package Persistence.M06_DataOrigin;

import Entities.Entity;
import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.IDAO;
import java.util.ArrayList;

public interface IDAOApplication extends IDAO {

    public ArrayList<Application> getApplication()throws DatabaseConnectionProblemException;

    public ArrayList<Application> getApplications(int companyId) throws DatabaseConnectionProblemException;

    public Application getApplication(int id) throws ApplicationNotFoundException, DatabaseConnectionProblemException;

    public Application getApplication(String token) throws ApplicationNotFoundException, DatabaseConnectionProblemException;

    public Application updateApplication(int id, int status) throws DatabaseConnectionProblemException, ApplicationNotFoundException;

    public Application createApplication (Entity app) throws DatabaseConnectionProblemException;
}
