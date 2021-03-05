package tech.uinb.tungus.service;

import java.util.concurrent.ExecutionException;

public interface ScheduleService {
    void scheduleGetFinalizedHead();

    void saveBlock() throws ExecutionException, InterruptedException;
}
