package io.wegetit.sau.core.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableMessageResolver
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class MessageResolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageResolverApplication.class, args);
    }
}
