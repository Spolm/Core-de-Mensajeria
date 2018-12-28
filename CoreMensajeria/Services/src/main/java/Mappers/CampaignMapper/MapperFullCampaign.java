package Mappers.CampaignMapper;

import DTO.M03_DTO.DTOFullCampaign;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Factory.DTOFactory;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperFullCampaign extends GenericMapper <DTOFullCampaign> {

    @Override
    public DTOFullCampaign CreateDto(Entity entity) {
        try {
            DTOFullCampaign dto = null;
            Campaign _cam = (Campaign) entity;
            dto = DTOFactory.CreateDtoFullCampaign(_cam.get_idCampaign(), _cam.get_nameCampaign(),
                                                   _cam.get_descCampaign(), _cam.get_statusCampaign(),
                                                   _cam.get_startCampaign(), _cam.get_endCampaign(),_cam.get_company());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }

    @Override
    public List<DTOFullCampaign> CreateDtoList(List<Entity> entities) {

        try
        {
            ArrayList<DTOFullCampaign> dtos = new ArrayList<>();

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

    @Override
    public List<Entity> CreateEntityList(List<DTOFullCampaign> dtos) {

        try
        {
            ArrayList<Entity> Campaign = new ArrayList<>();

            for (DTOFullCampaign _dtocam : dtos) {
                Campaign.add ( CreateEntity ( _dtocam ) );
            }

            return Campaign ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public Entity CreateEntity(DTOFullCampaign dto) {

        try {
            Campaign _cam = EntityFactory.CreateFullCampaign(dto.get_idCampaign(), dto.get_nameCampaign(),
                                                            dto.get_descCampaign(), dto.get_statusCampaign(),
                                                            dto.get_startCampaign(), dto.get_endCampaign(),
                                                             dto.get_company() );
            return _cam ;

        }
        catch (Exception e){
            throw e;
        }
    }

}
