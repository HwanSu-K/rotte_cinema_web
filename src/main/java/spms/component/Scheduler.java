package spms.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(cron="0 6 * * * *")
    public void HofScheduler() {
        //System.out.println("scheduled");
    }
}
