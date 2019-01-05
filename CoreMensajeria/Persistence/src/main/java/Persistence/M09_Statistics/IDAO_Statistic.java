package Persistence.M09_Statistics;

import Entities.Entity;
import Entities.M04_Integrator.Integrator;
import Exceptions.ChannelNotFoundException;
import Exceptions.CompanyDoesntExistsException;

import java.util.ArrayList;
import java.util.List;

public interface IDAO_Statistic {
    ArrayList<Entity> getAllCompanies(Integer userId) throws CompanyDoesntExistsException;

    ArrayList<Integrator> getIntegratorsForChannel(List<Integer> channelIds) throws ChannelNotFoundException;
}
