package io.wegetit.sau.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSystemInfo
@SpringBootApplication
public class SystemInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemInfoApplication.class, args);
    }
}
