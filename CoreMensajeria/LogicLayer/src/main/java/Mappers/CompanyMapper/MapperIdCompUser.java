package Mappers.CompanyMapper;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOIdCompUser;
import DTO.M02_DTO.DTOIdStatusCompany;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperIdCompUser extends GenericMapper<DTOIdCompUser> {
    /**
     * Metodo con el cual se transforma una entidad en un DTOIdStatusCompany
     *
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOIdStatusCompany
     */
    @Override
    public DTOIdCompUser CreateDto(Entity entity) {

        try {
            DTOIdCompUser dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.createDTOIdCompUser(_com.get_idCompany(), _com.get_idUser());

            return dto;

        } catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     *
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOIdCompUser
     */

    @Override
    public List<DTOIdCompUser> CreateDtoList(List<Entity> entities) {
        try {
            ArrayList<DTOIdCompUser> dtos = new ArrayList<>();
            for ( Entity _com : entities ) {
                dtos.add(CreateDto(_com));
            }
            return dtos;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Metodo con el cual se transforma de una lista de dtos a una lista de entidades
     *
     * @param dtos Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public List<Entity> CreateEntityList(List<DTOIdCompUser> dtos) {
        try {
            ArrayList<Entity> Company = new ArrayList<>();

            for (DTOIdCompUser _dtocom : dtos) {
                Company.add(CreateEntity(_dtocom));
            }

            return Company;

        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma un DTOIdCompUser a una Entidad Company
     *
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public Entity CreateEntity(DTOIdCompUser dto) {

        //  Logger logger = Logger.getLogger(M02_Companies.class.getName());
        //logger.info("Objeto compania recibido en Create Entity" + dto.get_idCompany() + " " +
        //           dto.is_status());

        try {
            Company _com = EntityFactory.CreateCompanyIDCompUser(dto.get_idCompany(), dto.get_idUser());
            return _com;

        } catch (Exception e) {
            throw e;
        }
    }
}