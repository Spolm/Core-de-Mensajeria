package Logic.M08_SendMessage;

import Entities.Entity;
import Entities.M07_Template.PlanningPackage.Planning;
import Entities.M07_Template.Template;
import Entities.M08_Validation.XMLManagement.Command;
import Entities.M08_Validation.XMLManagement.VerifiedParameter;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandScheduleMessage extends Command<Entity> {

    private VerifiedParameter _verifiedParameters;
    private Template _template;

    public CommandScheduleMessage(VerifiedParameter verifiedParameter) {
        _verifiedParameters = verifiedParameter;
    }

    @Override
    public void execute() {
        _template = _verifiedParameters.get_template();
        Planning planning = _template.getPlanning();
        Date startDate = planning.getStartDate();
        Date endDate = planning.getEndDate();
        addTimeToDate(startDate, planning.getStartTime());
        addTimeToDate(endDate, planning.getEndTime());

        if (planningIsValid(startDate, endDate)) {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            long mills = startDate.getTime() - System.currentTimeMillis();
            System.out.println(mills);
            System.out.println(System.currentTimeMillis());
            service.schedule(new ScheduleMessageTask(_verifiedParameters), mills, TimeUnit.MILLISECONDS);
        }


    }

    public Entity Return() {
        return null;
    }

    private boolean planningIsValid(Date startDate, Date endDate) {
        return (((startDate.compareTo(new Date()) < 0) || (startDate.compareTo(new Date()) == 0))
                && (startDate.compareTo(endDate)) < 0);
    }

    private Date addTimeToDate(Date date, String time) {
        Date fullDate = date;
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        try {
            Date timeDate = df.parse(time);
            fullDate.setTime(timeDate.getTime());
        } catch(ParseException e) {
            System.out.println(e);
        }

        return fullDate;
    }

}
