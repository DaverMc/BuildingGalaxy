package de.daver.build.hub.api.gate;

import java.util.concurrent.TimeUnit;

public interface SchedulerMaster {

    Scheduler scheduleRepeating(Runnable runnable, long delay, TimeUnit unit);


    interface Scheduler {
        void cancel();
    }
}
