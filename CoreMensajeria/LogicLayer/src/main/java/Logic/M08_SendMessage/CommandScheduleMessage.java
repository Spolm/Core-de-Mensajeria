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
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Clase que se encarga de insertar los mensajes que se van a enviar en una cola
 * para ser enviados en una fecha y hora determinada.
 * La fecha y la hora del envío es la fecha y hora de inicio especificados en la plantilla
 */

public class CommandScheduleMessage extends Command<Entity> {

    final static Logger log = LogManager.getLogger("CoreMensajeria");
    private VerifiedParameter _verifiedParameters;
    private Template _template;

    public CommandScheduleMessage(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    /**
     * @throws DateNotValidException
     * La excepción de lanza si alguna fecha no es válida, esto quiere decir,
     * si la fecha de inicio es menor a la fecha actual o si la fecha de fin es
     * mayor a la fecha de inicio.
     */
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

/*        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            startDate = df.parse("2019-01-09 9:10 PM");
            endDate = df.parse("2019-01-10 12:30 PM");
        } catch(ParseException e) {
        }*/

        System.out.println("Fecha de inicio de la plantilla: " + startDate);
        System.out.println("Fecha de fin de la plantilla: " + endDate);

        if (planningIsValid(startDate, endDate)) {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            long mills = startDate.getTime() - System.currentTimeMillis();
            service.schedule(new ScheduleMessageTask(_verifiedParameters, startDate), mills, TimeUnit.MILLISECONDS);
            log.info("El mensaje ha sido registrado para el envío");
            System.out.println("El mensaje ha sido registrado para el envío");
            log.info("El mensaje se enviará en " + mills + "milisegundos");
            System.out.println("El mensaje se enviará en " + mills + "milisegundos");
        } else {
            log.error("La fecha de inicio o fin es inválida");
            System.out.println("La fecha de inicio o fin es inválida");
            throw new DateNotValidException("Le fecha de fin no puede ser mayor a la fecha de inicio y la " +
                    "fecha de inicio no puede ser menor al día actual.");
        }


    }

    /**
     * @return
     */
    public Entity Return() {
        return null;
    }

    /**
     * @param startDate: Fecha de inicio de la plantilla
     * @param endDate: Fecha de fin de la plantilla
     * @return; Retorna true si la fecha de inicio es mayor o igual al día actual y la fecha fin
     * es mayor a la fecha de inicio y false si alguno de los casos anteriores falla
     */
    private boolean planningIsValid(Date startDate, Date endDate) {
        return (((startDate.compareTo(new Date()) > 0) || (startDate.compareTo(new Date()) == 0))
                && (startDate.compareTo(endDate)) < 0);
    }

    /**
     * @param date: Variable de tipo Date a la que se le desea agregar el tiempo
     * @param time: El tiempo en String que se desea agregar al día
     * @return: Retorna una variable de tipo Date con dia y hora
     * @throws DateNotValidException: Se lanza esta excepción en el caso de que la fecha
     * no cumpla con el formato definido por el SimpleDateFormat
     */
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
