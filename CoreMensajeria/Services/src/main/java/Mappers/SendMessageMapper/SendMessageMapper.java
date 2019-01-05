package Mappers.SendMessageMapper;

import DTO.ValidationDTO.ParametersDTO;
import Entities.Entity;
import Entities.M08_Validation.SentMessage;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.CommandValidate;
import Entities.M08_Validation.XMLManagement.CommandValidateMessage;
import Entities.M08_Validation.XMLManagement.CommandValidateTemplate;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.TemplateDoesntExistsException;


import java.util.List;

public class SendMessageMapper {

    public static Entity CreateEntity(ParametersDTO dto) {
        Command c = new CommandValidate(dto.get_idTemplate(), dto.get_message(), dto.get_channel());
        try {
            c.execute();
            Entity sentMessage = new SentMessage();
            ((SentMessage) sentMessage).set_templateId(dto.get_idTemplate());
            ((SentMessage) sentMessage).set_channel(dto.get_channel());
            ((SentMessage) sentMessage).set_message(dto.get_message());
            return sentMessage;
        } catch (TemplateDoesntExistsException e) {
            throw e;
        } catch (SMSTooLongException e) {
            throw e;
        } catch (ParameterDoesntExistsException e) {
            throw e;
        } catch (MessageDoesntExistsException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

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
