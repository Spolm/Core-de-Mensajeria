package Mappers.CompanyMapper;

import DTO.M02_DTO.DTOFullCompany;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Mappers.GenericMapper;
import DTO.DTOFactory;
import java.util.ArrayList;
import java.util.List;

public class MapperFullCompany extends GenericMapper <DTOFullCompany> {

    /**
     * Metodo con el cual se transforma una entidad en un DTOFullCompany
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOFullCompany
     */
    @Override
    public DTOFullCompany CreateDto(Entity entity) {

        try {
            DTOFullCompany dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.CreateDtoFullCompany(_com.get_idCompany(), _com.get_name(), _com.get_desc(),
                                                  _com.get_status(), _com.get_link(), _com.get_idUser() );

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOFullCompany
     */

    @Override
    public List<DTOFullCompany> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOFullCompany> dtos = new ArrayList<>();

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
    public List<Entity> CreateEntityList(List<DTOFullCompany> dtos) {
        try
        {
            ArrayList<Entity> Company = new ArrayList<>();

            for (DTOFullCompany _dtocom : dtos) {
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
    public Entity CreateEntity(DTOFullCompany dto) {
        try {

            Company _com = EntityFactory.CreateFullCompany(dto.get_idCompany(), dto.get_name(), dto.get_desc(),
                                                            dto.get_status(), dto.get_link(), dto.get_idUser());
            return _com ;

        }
        catch (Exception e){
            throw e;
        }
    }


}
