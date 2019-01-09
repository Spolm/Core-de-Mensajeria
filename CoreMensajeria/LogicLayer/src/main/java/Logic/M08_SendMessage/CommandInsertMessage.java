package Logic.M08_SendMessage;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.Template;
import Entities.M08_Validation.SentMessage;
import Logic.Command;
import Persistence.DAOFactory;
import Persistence.M08_SendMessage.IDAOSentMessage;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Comando para llamar al DAO e insertar el mensaje enviado en base de datos
 */

public class CommandInsertMessage extends Command {
    private Template _template;
    private int _integratorId;
    private int _channelId;
    private Date _time;

    /**
     * @param template recibe la plantilla para acceder al id del mensaje y de la aplicación
     * @param integratorId recibe el id del Integrador
     * @param channelId recibe el id del canal
     * @param time recibe la hora y la fecha del mensaje en Date para luego convertirla a Timestamp para almacenarla
     */
    public CommandInsertMessage(Template template, int integratorId, int channelId, Date time) {
        this._template = template;
        this._integratorId = integratorId;
        this._channelId = channelId;
        this._time = time;
    }

    /**
     Método que construye el objeto de la clase sentMessage para almacenarlo en base de datos
     */
    @Override
    public void execute() throws Exception {
        SentMessage sentMessage = EntityFactory.createSendMessage();
        sentMessage.set_applicationId(this._template.getApplication().get_id());
        sentMessage.set_campaignId(this._template.getCampaign().get_idCampaign());
        sentMessage.set_channel(this._channelId);
        sentMessage.set_integratorId(this._integratorId);
        sentMessage.set_message(this._template.getMessage().get_id());
        Timestamp time = new Timestamp(this._time.getTime());
        sentMessage.set_sentTime(time);
        IDAOSentMessage _dao = DAOFactory.instanciateDaoSentMessage();
        _dao.create(sentMessage);
    }

    public Entity Return() {
        return null;
    }
}
