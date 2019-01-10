package Mappers.CampaignMapper;

import DTO.DTOFactory;
import DTO.M03_DTO.DTOCampaignWithOut_Company;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M03_Campaign.Campaign;

import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperCampaignWithOut_Company extends GenericMapper <DTOCampaignWithOut_Company> {


    /**
     * Metodo con el cual se transforma una entidad en un DTOCampaignWithOut_Company
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOCampaignWithOut_Company
     */
    @Override
    public DTOCampaignWithOut_Company CreateDto(Entity entity) {
        try {
            DTOCampaignWithOut_Company dto = null;
            Campaign _cam = (Campaign) entity;
            dto = DTOFactory.CreateDtoCampaignWithOut_Company(_cam.get_idCampaign(), _cam.get_nameCampaign(),
                    _cam.get_descCampaign(), _cam.get_statusCampaign(),
                    _cam.get_startCampaign(), _cam.get_endCampaign());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOCampaignWithOut_Company
     */
    @Override
    public List<DTOCampaignWithOut_Company> CreateDtoList(List<Entity> entities) {

        try
        {
            ArrayList<DTOCampaignWithOut_Company> dtos = new ArrayList<>();

            for (Entity _cam : entities) {
                dtos.add( CreateDto( _cam ) );
            }

            return dtos;

        }
        catch (Exception e)
        {
            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de dtos a una lista de entidades
     * @param dtos Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
    @Override
    public List<Entity> CreateEntityList(List<DTOCampaignWithOut_Company> dtos) {
        try
        {
            ArrayList<Entity> Campaign = new ArrayList<>();

            for (DTOCampaignWithOut_Company _dtocam : dtos) {
                Campaign.add ( CreateEntity ( _dtocam ) );
            }

            return Campaign ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma un DTOCampaignWithOut_Company a una Entidad Campaign
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
    @Override
    public Entity CreateEntity(DTOCampaignWithOut_Company dto) {
        try {
            Campaign _cam = EntityFactory.CreateCampaignWithOut_Company(dto.get_idCampaign(), dto.get_nameCampaign(),
                    dto.get_descCampaign(), dto.get_statusCampaign(),
                    dto.get_startCampaign(), dto.get_endCampaign() );
            return _cam ;

        }
        catch (Exception e){
            throw e;
        }
    }

}
