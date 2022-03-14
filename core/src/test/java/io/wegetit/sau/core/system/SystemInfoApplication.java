package io.wegetit.sau.core.system;

import io.wegetit.sau.core.system.data.SystemBuild;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.annotation.Validated;

@EnableSystemInfo
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class SystemInfoApplication {

    @Validated
    @Bean
    @ConfigurationProperties(prefix = "test.system.build")
    public SystemBuild systemBuild() {
        return new SystemBuild();
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemInfoApplication.class, args);
    }
}
