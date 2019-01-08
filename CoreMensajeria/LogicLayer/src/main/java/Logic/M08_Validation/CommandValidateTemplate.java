package Logic.M08_Validation;

import Entities.M07_Template.StatusPackage.Status;
import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.TemplateNotApprovedException;
import Exceptions.UnexpectedErrorException;
import Logic.CommandsFactory;
import Logic.M07_Template.CommandGetTemplate;

import java.util.logging.Logger;

/**
 * Comando para validar plantillas
 */
public class CommandValidateTemplate extends CommandValidateParameter {
    int _id;

    /**
     * @param _id recibe el id de una plantilla
     */
    public CommandValidateTemplate(int _id) {
        this._id = _id;
    }

    /**
     * @throws TemplateDoesntExistsException cuando no existe la plantilla que se está buscando
     */
    public void execute() throws Exception {
        set_valid(false);
        Logger logger = Logger.getLogger(CommandValidateParameter.class.getName());
        CommandGetTemplate c = CommandsFactory.createCommandGetTemplate(this._id);
        try {
            c.execute();
            Template t = c.Return();
            if (t.get_id() > 0) {
                if (t.getStatus().getStatusName().equals("Aprobado"))
                    this.set_valid(true);
                else {
                    logger.warning("Status Plantilla: " + t.getStatus().getStatusName());
                    this.set_valid(false);
                    throw new TemplateNotApprovedException();
                }
            }
            else {
                logger.warning("Plantilla no Existe");
                this.set_valid(false);
                throw new TemplateDoesntExistsException();
            }
        } catch (TemplateDoesntExistsException e) {
            logger.warning("Plantilla no Existe");
            this.set_valid(false);
            throw e;
        }
    }

    @Override
    public Boolean Return() {
        return this.get_valid();
    }
}
