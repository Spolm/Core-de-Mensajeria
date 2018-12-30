package Persistence.M09_Statistics;

import Entities.Entity;
import Exceptions.CompanyDoesntExistsException;

import java.util.ArrayList;

public interface IDAO_Statistic {
    ArrayList<Entity> getAllCompanies(Integer userId) throws CompanyDoesntExistsException;
}
