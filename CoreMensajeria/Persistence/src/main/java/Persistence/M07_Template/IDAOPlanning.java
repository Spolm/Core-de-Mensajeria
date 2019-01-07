package Persistence.M07_Template;

import Entities.Entity;
import Entities.M07_Template.PlanningPackage.Planning;
import Persistence.IDAO;

public interface IDAOPlanning extends IDAO {
    Entity getPlanning(int templateId);
    void postPlanning(Planning planning, int templateId);
    void updatePlanning (Planning planning, int templateId);
}
