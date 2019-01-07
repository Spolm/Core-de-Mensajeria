package Logic.M08_Validation;

import DTO.M08_DTO.ParametersDTO;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Logic.CommandsFactory;

public class CommandValidate extends CompositeCommand<Boolean> {
    private boolean _valid = false;

    public CommandValidate(ParametersDTO dto) {
        Command[] commandList = new Command[2];
        commandList[0] = CommandsFactory.createCommandValidateMessage(dto.get_idTemplate(), dto.get_message(),
                                                                        dto.get_channel());
        commandList[1] = CommandsFactory.createCommandValidateTemplate(dto.get_idTemplate());
        set_commandList(commandList);
    }



    @Override
    public void execute() throws Exception{
        Command[] commandList = get_commandList();
        try {
            for (Command c : commandList) {
                c.execute();
                _valid = true;
            }
        } catch (TemplateDoesntExistsException e) {
            throw e;
        } catch (MessageDoesntExistsException e) {
            throw e;
        } catch (ParameterDoesntExistsException e) {
            throw  e;
        } catch (SMSTooLongException e) {
            throw e;
        } catch (Exception e) {
            throw new UnexpectedErrorException();
        }

    }

    @Override
    public Boolean Return() {
        return _valid;
    }
}
