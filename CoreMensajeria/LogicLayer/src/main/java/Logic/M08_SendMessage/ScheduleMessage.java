package Logic.M08_SendMessage;

import Entities.Entity;
import Logic.Command;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleMessage extends Command {

    @Override
    public void execute() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 30);
        long mills = calendar.getTimeInMillis() - System.currentTimeMillis();
        System.out.println(mills);
        System.out.println(System.currentTimeMillis());
        service.schedule(new ScheduleMessageTask(), mills, TimeUnit.MILLISECONDS);
    }

    @Override
    public Entity Return() {
        return null;
    }
}
