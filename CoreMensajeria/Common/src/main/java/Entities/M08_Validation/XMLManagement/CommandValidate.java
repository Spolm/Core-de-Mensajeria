package Entities.M08_Validation.XMLManagement;

import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.TemplateDoesntExistsException;

public class CommandValidate extends CompositeCommand {

    public CommandValidate(int template, String message, String channel) {
        Command[] commandList = new Command[2];
        commandList[0] = new CommandValidateMessage(template, message, channel);
        commandList[1] = new CommandValidateTemplate(template);
        set_commandList(commandList);
    }



    @Override
    public void execute() throws TemplateDoesntExistsException, MessageDoesntExistsException, ParameterDoesntExistsException, SMSTooLongException{
        Command[] commandList = get_commandList();
        try {
            for (Command c : commandList) {
                c.execute();
            }
        } catch (TemplateDoesntExistsException e) {
            throw e;
        } catch (MessageDoesntExistsException e) {
            throw e;
        } catch (ParameterDoesntExistsException e) {
            throw  e;
        } catch (SMSTooLongException e) {
            throw e;
        }

    }
}
