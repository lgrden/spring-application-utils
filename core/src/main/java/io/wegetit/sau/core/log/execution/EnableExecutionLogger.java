package io.wegetit.sau.core.log.execution;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableExecutionLogger.ExecutionLoggerConfiguration.class)
public @interface EnableExecutionLogger {

    @Configuration
    class ExecutionLoggerConfiguration extends BaseConfiguration {

        @Bean
        public ExecutionLoggerService executionLoggerService() {
            return new ExecutionLoggerService();
        }
    }
}
