package io.wegetit.sau.core.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableSystemInfo
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class SystemInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemInfoApplication.class, args);
    }
}
