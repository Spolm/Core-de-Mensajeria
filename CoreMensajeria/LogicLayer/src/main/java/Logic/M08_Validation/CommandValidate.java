package Logic.M08_Validation;

import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.SMSTooLongException;
import Exceptions.UnexpectedErrorException;
import Logic.Command;
import Logic.CommandsFactory;

/**
 * Comando compuesto que ejecuta todos los comandos que validen
 * los parametros de envio de un mensaje
 *
 * @see CompositeCommand
 */
public class CommandValidate extends CompositeCommand<Boolean> {
    private boolean _valid = false;

    /**
     * Constructor del comando que recibe los parametros
     *
     * @param parameters parámetros estandar que se reciben tanto de aplicación como de archivo
     */
    public CommandValidate(VerifiedParameter parameters) {
        Command[] commandList = new Command[2];
        commandList[0] = CommandsFactory.createCommandValidateTemplate(parameters.get_template().get_id());
        commandList[1] = CommandsFactory.createCommandValidateMessage(parameters.get_template().get_id(),
                parameters.get_verifiedMessages());
        set_commandList(commandList);
    }


    /**
     * Metodo que ejecuta los comandos de validacion
     *
     * @throws SMSTooLongException
     * @throws UnexpectedErrorException
     * @throws TemplateDoesntExistsException
     */
    @Override
    public void execute() throws SMSTooLongException, UnexpectedErrorException, TemplateDoesntExistsException {
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
