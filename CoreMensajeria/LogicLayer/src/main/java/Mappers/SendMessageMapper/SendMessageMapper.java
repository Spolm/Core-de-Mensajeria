package Mappers.SendMessageMapper;

import DTO.M08_DTO.ParametersDTO;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M08_Validation.SentMessage;
import Exceptions.*;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;


import java.util.List;

public class SendMessageMapper {

    public static SentMessage CreateEntity(ParametersDTO dto) {
        SentMessage sentMessage = EntityFactory.createSendMessage();
        sentMessage.set_templateId(dto.get_idTemplate());
        return sentMessage;
    }


    public ParametersDTO CreateDto(Entity entity) {
        return null;
    }


    public List<ParametersDTO> CreateDtoList(List<Entity> entities) {
        return null;
    }


    public List<Entity> CreateEntityList(List<ParametersDTO> parametersDTOS) {
        return null;
    }
}
