package Logic.M08_SendMessage;

import Entities.Entity;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import Exceptions.M08_SendMessageManager.DateNotValidException;
import Logic.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandScheduleMessage extends Command<Entity> {

    final static Logger log = LogManager.getLogger("CoreMensajeria");
    private VerifiedParameter _verifiedParameters;
    private Template _template;

    public CommandScheduleMessage(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    @Override
    public void execute() throws DateNotValidException {
        log.info("El mensaje será registrado");
        _template = _verifiedParameters.get_template();
        Planning planning = _template.getPlanning();
        Date startDate = planning.getStartDate();
        Date endDate = planning.getEndDate();
        addTimeToDate(startDate, planning.getStartTime());
        addTimeToDate(endDate, planning.getEndTime());

        log.info("Fecha de inicio de la plantilla: " + startDate);
        log.info("Fecha de fin de la plantilla: " + endDate);

        if (planningIsValid(startDate, endDate)) {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            long mills = startDate.getTime() - System.currentTimeMillis();
            log.info("El mensaje se enviará en " + mills + "milisegundos");
            service.schedule(new ScheduleMessageTask(_verifiedParameters), mills, TimeUnit.MILLISECONDS);
            log.info("El mensaje ha sido registrado para el envío");
        } else {
            log.error("La fecha de inicio o fin es inválida");
            throw new DateNotValidException("Le fecha de fin no puede ser mayor a la fecha de inicio y la " +
                    "fecha de inicio no puede ser menor al día actual.");
        }


    }

    public Entity Return() {
        return null;
    }

    private boolean planningIsValid(Date startDate, Date endDate) {
        return (((startDate.compareTo(new Date()) > 0) || (startDate.compareTo(new Date()) == 0))
                && (startDate.compareTo(endDate)) < 0);
    }

    private Date addTimeToDate(Date date, String time) throws DateNotValidException {
        DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateToString.format(date);
        Date fullDate = date;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            fullDate = df.parse(stringDate + " " + time);
            System.out.println("Time: " + fullDate);
        } catch(ParseException e) {
            throw new DateNotValidException("El formato de la fecha u hora es inválido");
        }

        return fullDate;
    }

}
