package io.wegetit.sau.core.log.exception;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableExceptionLogger.ExceptionLoggerConfiguration.class)
public @interface EnableExceptionLogger {

    @Configuration
    class ExceptionLoggerConfiguration extends BaseConfiguration {

        @Bean
        public ExceptionLoggerService exceptionLoggerService() {
            return new ExceptionLoggerService();
        }
    }
}
