package io.wegetit.sau.core.errorhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;

@EnableErrorHandler
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class ErrorHandlerApplication {

    @Autowired
    private ErrorHandlerService errorHandlerService;

    @PostConstruct
    private void init() {
        errorHandlerService.registerType(ExceptionType.builder().errorClass(EntityNotFoundException.class)
            .status(HttpStatus.NOT_FOUND).evalMessage(p -> "MSG: " + p.getMessage()).build());
    }

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlerApplication.class, args);
    }
}
