package io.wegetit.sau.manifest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableManifestEndpoint
@Configuration
public class ManifestEndpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManifestEndpointApplication.class, args);
    }
}
