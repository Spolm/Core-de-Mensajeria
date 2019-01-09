package Mappers.CompanyMapper;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdStatusCompany;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MapperIdStatusCompany  extends GenericMapper<DTOIdStatusCompany> {
    /**
     * Metodo con el cual se transforma una entidad en un DTOIdCompany
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOIdCompany
     */
    @Override
    public DTOIdStatusCompany CreateDto(Entity entity) {

        try {
            DTOIdStatusCompany dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.CreateDTOIdStatusCompany( _com.get_idCompany(), _com.get_status() );

            return dto;
        }
        catch ( Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOIdCompany
     */

    @Override
    public List<DTOIdStatusCompany> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOIdStatusCompany> dtos = new ArrayList<>();
            for (Entity _com : entities) {
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
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public List<Entity> CreateEntityList(List<DTOIdStatusCompany> dtos) {
        try
        {
            ArrayList<Entity> Company = new ArrayList<>();

            for (DTOIdStatusCompany _dtocom : dtos) {
                Company.add ( CreateEntity ( _dtocom ) );
            }

            return Company ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma un DTOFullCompany a una Entidad Company
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public Entity CreateEntity( DTOIdStatusCompany dto ) {

         Logger logger = Logger.getLogger(MapperIdStatusCompany.class.getName());
         logger.info("Objeto compania recibido en Create Entity" + dto.get_idCompany() + " " +
                      dto.is_status());

        try {
            Company _com = EntityFactory.CreateCompanyIDStatus( dto.get_idCompany(), dto.is_status() );
            return _com ;

        }
        catch (Exception e){
            throw e;
        }
    }
}
