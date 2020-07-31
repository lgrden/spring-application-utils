package io.wegetit.sau.manifest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "io.wegetit.sau.manifest.*"})
public class ManifestEndpointConfiguration {
}
