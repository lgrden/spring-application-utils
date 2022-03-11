package io.wegetit.sau.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableSwaggerIndexRedirect
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class SwaggerIndexRedirectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerIndexRedirectApplication.class, args);
    }
}
