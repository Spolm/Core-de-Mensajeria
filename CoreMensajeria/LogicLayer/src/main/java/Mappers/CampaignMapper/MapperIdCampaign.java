package Mappers.CampaignMapper;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdCompany;
import DTO.M03_DTO.DTOIdCampaign;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M03_Campaign.Campaign;

import Mappers.GenericMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MapperIdCampaign  extends GenericMapper< DTOIdCampaign > {

    /**
     * Metodo con el cual se transforma una entidad en un DTOIdCompany
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOIdCampaign
     */
    @Override
    public DTOIdCampaign CreateDto( Entity entity ) {

        try {
            DTOIdCampaign _dto = null;
            Campaign _cam = ( Campaign ) entity;
            _dto = DTOFactory.CreateDTOIdCampaign( _cam.get_idCampaign() );

            return _dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOIdCampaign
     */

    @Override
    public List< DTOIdCampaign > CreateDtoList( List<Entity> entities ) {
        try
        {
            ArrayList< DTOIdCampaign > dtos = new ArrayList<>();

            for ( Entity _com : entities ) {
                dtos.add( CreateDto( _com ) );
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
    public List<Entity> CreateEntityList( List<DTOIdCampaign> dtos ) {
        try
        {
            ArrayList< Entity > Campaign = new ArrayList<>();

            for ( DTOIdCampaign _dtocam : dtos ) {
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
     * Metodo con el cual se transforma un DTOIdCampaign a una Entidad Campaign
     * @param _dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
    @Override
    public Entity CreateEntity( DTOIdCampaign _dto ) {

        //Logger logger = Logger.getLogger(M03_Campaigns.class.getName());
      //  logger.info("Objeto compania recibido en Create Entity" + _dto.get_idCampaign());

        try {
            Campaign  _cam = EntityFactory.CreateCampaignId( _dto.get_idCampaign() );
            return _cam ;

        }
        catch (Exception e){
            throw e;
        }
    }
}
