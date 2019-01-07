package Mappers.CompanyMapper;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdCompany;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Mappers.GenericMapper;


import java.util.ArrayList;
import java.util.List;

public class MapperIdCompany extends GenericMapper<DTOIdCompany> {

    /**
     * Metodo con el cual se transforma una entidad en un DTOIdCompany
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOIdCompany
     */
    @Override
    public DTOIdCompany CreateDto(Entity entity) {

        try {
            DTOIdCompany dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.CreateDTOIdCompany(_com.get_idCompany());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOIdCompany
     */

    @Override
    public List<DTOIdCompany> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOIdCompany> dtos = new ArrayList<>();

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
    public List<Entity> CreateEntityList(List<DTOIdCompany> dtos) {
        try
        {
            ArrayList<Entity> Company = new ArrayList<>();

            for (DTOIdCompany _dtocom : dtos) {
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
    public Entity CreateEntity(DTOIdCompany dto) {

        Logger logger = Logger.getLogger(MapperIdCompany.class.getName());
        logger.info("Objeto compania recibido en Create Entity" + dto.get_idCompany());

        try {
            Company _com = EntityFactory.CreateCompanyOnlyId(dto.get_idCompany());
            return _com ;

        }
        catch (Exception e){
            throw e;
        }
    }
}
