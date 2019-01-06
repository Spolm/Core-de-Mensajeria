package Persistence.M07_Template;

import Entities.Entity;
import Persistence.IDAO;

public interface IDAOPlanning extends IDAO {
    Entity getPlanning(int templateId);
    void postPlanning(String[] planning, int templateId);
    void updatePlanning (String[] planning, int templateId);
}
