package io.wegetit.sau.core.log.execustion;

import io.wegetit.sau.core.log.execution.LogExecution;

import java.util.concurrent.TimeUnit;

public class ExecutionTestService {

    @LogExecution
    public void execute() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }
}
