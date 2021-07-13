package spms.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(cron="1 * * * * *")
    public void HofScheduler() {
        //System.out.println("scheduled");
    }
}
