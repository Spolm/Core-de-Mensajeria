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

    public static Entity CreateEntity(ParametersDTO dto) {
        Entity sentMessage = EntityFactory.createSendMessage();
        ((SentMessage) sentMessage).set_templateId(dto.get_idTemplate());
        ((SentMessage) sentMessage).set_channel(dto.get_channel());
        ((SentMessage) sentMessage).set_message(dto.get_message());
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
