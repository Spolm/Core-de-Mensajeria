package Logic.M08_Validation;

import Entities.M07_Template.Template;
import Exceptions.M07_Template.TemplateDoesntExistsException;
import Exceptions.M08_SendMessageManager.TemplateNotApprovedException;
import Logic.CommandsFactory;
import Logic.M07_Template.CommandGetTemplate;

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
     * Realiza todas las validaciones a una plantilla
     * @throws TemplateDoesntExistsException cuando no existe la plantilla que se está buscando
     */
    public void execute() throws Exception {
        set_valid(false);
        CommandGetTemplate c = CommandsFactory.createCommandGetTemplate(this._id);
        try {
            c.execute();
            Template t = c.Return();
            if (t.get_id() > 0) {
                if (t.getStatus().getStatusName().equals("Aprobado")) {
                    log.info("Validación de la Plantilla Coorrecta");
                    this.set_valid(true);
                }
                else {
                    log.error("Status Plantilla: " + t.getStatus().getStatusName());
                    this.set_valid(false);
                    throw new TemplateNotApprovedException();
                }
            }
            else {
                log.error("Plantilla no Existe");
                this.set_valid(false);
                throw new TemplateDoesntExistsException();
            }
        } catch (TemplateDoesntExistsException e) {
            log.error("Plantilla no Existe");
            this.set_valid(false);
            throw e;
        }
    }

    @Override
    public Boolean Return() {
        return this.get_valid();
    }
}
