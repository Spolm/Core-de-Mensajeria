package Mappers.CompanyMapper;

import DTO.DTO;
import DTO.M02_DTO.DTOFullCompany;
import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M02_Company.Company;
import Factory.DTOFactory;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperFullCompany extends GenericMapper <DTOFullCompany> {

    @Override
    public DTOFullCompany CreateDto(Entity entity) {

        try {
            DTOFullCompany dto = null;
            Company _com = (Company) entity;
            dto = DTOFactory.CreateDtoFullCompany(_com.get_idCompany(), _com.get_name(), _com.get_desc(),
                                                  _com.get_status(), _com.get_link() );

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }


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

    @Override
    public Entity CreateEntity(DTOFullCompany dto) {

        try {
            Company _com = EntityFactory.CreateFullCompany(dto.get_idCompany(), dto.get_name(), dto.get_desc(),
                                                            dto.get_status(), dto.get_link());
            return _com ;

        }
        catch (Exception e){
            throw e;
        }
    }


}
