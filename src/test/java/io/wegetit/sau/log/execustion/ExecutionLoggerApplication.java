package io.wegetit.sau.log.execustion;

import io.wegetit.sau.log.execution.EnableExecutionLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableExecutionLogger
@SpringBootApplication
public class ExecutionLoggerApplication {

    @Bean
    public ExecutionTestService executionTestService() {
        return new ExecutionTestService();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExecutionLoggerApplication.class, args);
    }
}