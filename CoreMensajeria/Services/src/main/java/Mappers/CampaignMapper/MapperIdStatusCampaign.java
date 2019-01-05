package Mappers.CampaignMapper;

import DTO.M02_DTO.DTOIdStatusCompany;
import DTO.M03_DTO.DTOIdStatusCampaign;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Factory.DTOFactory;
import Mappers.GenericMapper;
import webService.M02_CompanyManagement.M02_Companies;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MapperIdStatusCampaign extends GenericMapper<DTOIdStatusCampaign> {

    /**
     * Metodo con el cual se transforma una entidad en un DTOIdStatusCampaign
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOIdStatusCampaign
     */
    @Override
    public DTOIdStatusCampaign CreateDto( Entity entity ) {

        try {
            DTOIdStatusCampaign dto = null;
            Campaign _com = (Campaign) entity;
            dto = DTOFactory.CreateDTODTOIdStatusCampaign( _com.get_idCampaign(), _com.get_statusCampaign() );

            return dto;
        }
        catch ( Exception e ) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOIdStatusCampaign
     */

    @Override
    public List<DTOIdStatusCampaign> CreateDtoList( List<Entity> entities ) {
        try
        {
            ArrayList<DTOIdStatusCampaign> dtos = new ArrayList<>();
            for ( Entity _cam : entities ) {
                dtos.add( CreateDto( _cam ) );
            }
            return dtos;
        }
        catch ( Exception e )
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
    public List<Entity> CreateEntityList( List<DTOIdStatusCampaign> dtos ) {
        try
        {
            ArrayList<Entity> Campaign = new ArrayList<>();

            for ( DTOIdStatusCampaign _dtocam : dtos) {
                Campaign.add ( CreateEntity ( _dtocam ) );
            }

            return Campaign ;

        }
        catch ( Exception e )
        {
            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma un DTOIdStatusCampaign a una Entidad Campaign
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public Entity CreateEntity( DTOIdStatusCampaign dto ) {

        Logger logger = Logger.getLogger(M02_Companies.class.getName());
        logger.info("Objeto compania recibido en Create Entity" + dto.get_idCampaign() + " " +
                dto.is_status());

        try {
            Campaign _com = EntityFactory.CreateCampaignIDStatus( dto.get_idCampaign(), dto.is_status() );
            return _com ;

        }
        catch ( Exception e ){
            throw e;
        }
    }
}
