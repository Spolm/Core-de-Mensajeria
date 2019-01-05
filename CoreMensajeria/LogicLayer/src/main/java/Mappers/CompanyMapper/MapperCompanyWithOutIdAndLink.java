package Mappers.CompanyMapper;

import DTO.DTOFactory;
import DTO.M02_DTO.DTOCompanyWithOutIdAndLink;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperCompanyWithOutIdAndLink extends GenericMapper <DTOCompanyWithOutIdAndLink> {



    /**
     * Metodo con el cual se transforma una entidad en un DTOCompanyWithOutIdAndLink
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOCompanyWithOutIdAndLink
     */
    @Override
    public DTOCompanyWithOutIdAndLink CreateDto(Entity entity) {
        try {
            DTOCompanyWithOutIdAndLink dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.CreateDtoCompanyWithOut_idAndlink(_com.get_name(), _com.get_desc(), _com.get_status());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOCompanyWithOutIdAndLink
     */
    @Override
    public List<DTOCompanyWithOutIdAndLink> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOCompanyWithOutIdAndLink> dtos = new ArrayList<>();

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
    public List<Entity> CreateEntityList(List<DTOCompanyWithOutIdAndLink> dtos) {
        try
        {
            ArrayList<Entity> Company = new ArrayList<>();

            for (DTOCompanyWithOutIdAndLink _dtocom : dtos) {
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
     * Metodo con el cual se transforma un DTOCompanyWithOutIdAndLink a una Entidad Company
     * @param dto Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Company
     */
    @Override
    public Entity CreateEntity(DTOCompanyWithOutIdAndLink dto) {
        try {
            Company _com = EntityFactory.CreateCompanyWithOutID(dto.get_name(), dto.get_desc(),
                    dto.get_status());
            return _com ;

        }
        catch (Exception e){
            throw e;
        }
    }
}
