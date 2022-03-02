package io.wegetit.sau.log.execustion;

import io.wegetit.sau.log.execution.LogExecution;

import java.util.concurrent.TimeUnit;

public class ExecutionTestService {

    @LogExecution
    public void execute() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }
}
