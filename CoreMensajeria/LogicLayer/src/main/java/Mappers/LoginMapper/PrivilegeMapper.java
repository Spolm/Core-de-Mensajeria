package Mappers.LoginMapper;

import DTO.DTOFactory;
import DTO.M01_DTO.DTOPrivilege;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.Privilege;

import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeMapper extends GenericMapper <DTOPrivilege> {


    /**
     * Metodo con el cual se transforma una entidad en un DTOFullPrivilege
     * @param entity Entidad que recibe para hacer el mapeo
     * @return un objeto del tipo DTOFullCampaign
     */
    @Override
    public DTOPrivilege CreateDto(Entity entity) {
        try {
            DTOPrivilege dto = null;
            Privilege _pri = (Privilege) entity;
            dto = DTOFactory.CreateDTOPrivilege(_pri.get_idPrivileges(), _pri.get_codePrivileges(),
                                                   _pri.get_actionPrivileges());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }

    /**
     * Metodo con el cual se transforma de una lista de entidades a una lista de dto
     * @param entities Entidad que recibe para hacer el mapeo
     * @return una lista de objetos del tipo DTOFullPrivilege
     */
    @Override
    public List<DTOPrivilege> CreateDtoList(List<Entity> entities) {

        try
        {
            ArrayList<DTOPrivilege> dtos = new ArrayList<>();

            for (Entity _pri : entities) {
                dtos.add( CreateDto( _pri ) );
            }

            return dtos;

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
    public Entity CreateEntity(DTOPrivilege dto) {

        try {
            Entity _pri = EntityFactory.privilege(dto.get_idPrivileges(), dto.get_codePrivileges(),
                                                            dto.get_actionPrivileges());
            return _pri ;

        }
        catch (Exception e){
            throw e;
        }
    }
    /**
     * Metodo con el cual se transforma de una lista de dtos a una lista de entidades
     * @param dtos Entidad que recibe para hacer el mapeo
     * @return una lista de Entidades del tipo Campaign
     */
    @Override
    public List<Entity> CreateEntityList(List<DTOPrivilege> dtos) {

        try
        {
            ArrayList<Entity> Privilege = new ArrayList<>();

            for (DTOPrivilege _dtopri : dtos) {
                Privilege.add ( CreateEntity ( _dtopri ) );
            }

            return Privilege ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
