package io.wegetit.sau.errorhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableErrorHandler
@SpringBootApplication
public class ErrorHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlerApplication.class, args);
    }
}
