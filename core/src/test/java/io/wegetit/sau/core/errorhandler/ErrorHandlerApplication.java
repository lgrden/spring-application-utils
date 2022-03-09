package io.wegetit.sau.core.errorhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableErrorHandler
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class ErrorHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlerApplication.class, args);
    }
}
