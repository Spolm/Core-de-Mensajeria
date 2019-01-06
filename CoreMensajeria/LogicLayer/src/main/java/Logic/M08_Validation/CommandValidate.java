package Logic.M08_Validation;

import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.TemplateDoesntExistsException;
import Logic.Command;
import Logic.CommandsFactory;

public class CommandValidate extends CompositeCommand<Boolean> {
    private boolean _valid = false;

    public CommandValidate(int template, String message, String channel) {
        Command[] commandList = new Command[2];
        commandList[0] = CommandsFactory.createCommandValidateMessage(template, message, channel);
        commandList[1] = CommandsFactory.createCommandValidateTemplate(template);
        set_commandList(commandList);
    }



    @Override
    public void execute() throws TemplateDoesntExistsException, MessageDoesntExistsException, ParameterDoesntExistsException, SMSTooLongException{
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
        } catch (Exception e) {}

    }

    @Override
    public Boolean Return() {
        return _valid;
    }
}
