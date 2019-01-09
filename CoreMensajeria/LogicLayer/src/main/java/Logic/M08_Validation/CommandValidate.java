package Logic.M08_Validation;

import DTO.M08_DTO.ParametersDTO;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Exceptions.SMSTooLongException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Logic.CommandsFactory;

/**
 * Comando compuesto que ejecuta todos los comandos que validen los parametros de envio de un mensaje
 */
public class CommandValidate extends CompositeCommand<Boolean> {
    private boolean _valid = false;

    /**
     * @param dto el json que se recibe de la aplicacion
     */
    public CommandValidate(ParametersDTO dto) {
        Command[] commandList = new Command[2];
        commandList[0] = CommandsFactory.createCommandValidateTemplate(dto.get_idTemplate());
        commandList[1] = CommandsFactory.createCommandValidateMessage(dto.get_idTemplate());
        set_commandList(commandList);
    }


    /**
     * @throws SMSTooLongException
     * @throws UnexpectedErrorException
     * @throws TemplateDoesntExistsException
     */
    @Override
    public void execute() throws SMSTooLongException, UnexpectedErrorException, TemplateDoesntExistsException{
        Command[] commandList = get_commandList();
        try {
            for (Command c : commandList) {
                c.execute();
                _valid = true;
            }
        } catch (TemplateDoesntExistsException e) {
            throw e;
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
