package Mappers.CampaignMapper;

import DTO.DTOFactory;
import DTO.M03_DTO.DTOFullCampaign;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M03_Campaign.Campaign;

import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MapperFullCampaign extends GenericMapper <DTOFullCampaign> {


    /**
     * Metodo con el cual se transforma una entidad en un DTOFullCampaign
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOFullCampaign
     */
    @Override
    public DTOFullCampaign CreateDto( Entity entity ) {
        try {
            DTOFullCampaign dto = null;
            Campaign _cam = ( Campaign ) entity;
            dto = DTOFactory.CreateDtoFullCampaign(_cam.get_idCampaign(), _cam.get_nameCampaign(),
                                                   _cam.get_descCampaign(), _cam.get_statusCampaign(),
                                                   _cam.get_startCampaign(), _cam.get_endCampaign(),
                                                   _cam.get_idCompany());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOFullCampaign
     */
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


    /**
     * Metodo con el cual se transforma de una lista de dtos a una lista de entidades
     * @param dtos Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
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


    /**
     * Metodo con el cual se transforma un DTOFullCampaign a una Entidad Campaign
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
    @Override
    public Entity CreateEntity(DTOFullCampaign dto) {
        Logger logger = Logger.getLogger(MapperFullCampaign.class.getName());
        logger.info("Objeto compania recibido en MAPPERfull" + dto.get_idCampaign() + " " +
                    dto.get_nameCampaign() + " "+ dto.get_statusCampaign() + " " + dto.get_descCampaign()+""+
                    dto.get_startCampaign()+""+dto.get_endCampaign()+"id:"+dto.get_idCompany() );

        try {
            Campaign _cam = EntityFactory.CreateFullCampaign(dto.get_idCampaign(), dto.get_nameCampaign(),
                                                            dto.get_descCampaign(), dto.get_statusCampaign(),
                                                            dto.get_startCampaign(), dto.get_endCampaign(),
                                                            dto.get_idCompany() );
            return _cam ;

        }
        catch (Exception e){
            throw e;
        }
    }

}
