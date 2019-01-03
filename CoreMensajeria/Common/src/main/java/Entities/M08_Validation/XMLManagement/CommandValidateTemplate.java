package Entities.M08_Validation.XMLManagement;

import Entities.M07_Template.HandlerPackage.TemplateHandler;
import Entities.M07_Template.Template;
import Exceptions.TemplateDoesntExistsException;

import java.util.logging.Logger;

public class CommandValidateTemplate extends CommandValidateParameter {
    int _id;

    public CommandValidateTemplate(int _id) {
        this._id = _id;
    }

    public void execute() throws Exception{
        Logger logger = Logger.getLogger(CommandValidateParameter.class.getName());
        TemplateHandler template = new TemplateHandler();
        try{
            Template t=template.getTemplate(this._id);
            if (t.getTemplateId()>0)
                this.set_valid(true);
        } catch (TemplateDoesntExistsException e) {
            logger.warning("Plantilla no Existe");
            this.set_valid(false);
            this.set_response("Plantilla no Existe");
            throw e;
        }
    }
}
