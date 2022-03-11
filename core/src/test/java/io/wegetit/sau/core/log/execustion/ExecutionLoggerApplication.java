package io.wegetit.sau.core.log.execustion;

import io.wegetit.sau.core.log.execution.EnableExecutionLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableExecutionLogger
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class ExecutionLoggerApplication {

    @Bean
    public ExecutionTestService executionTestService() {
        return new ExecutionTestService();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExecutionLoggerApplication.class, args);
    }
}
