package io.wegetit.sau.core.slack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

@EnableSlackMessaging
@SpringBootApplication
public class SlackMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlackMessagingApplication.class, args);
    }

    @Primary
    @Bean
    @Validated
    @ConfigurationProperties(prefix = "slack.test")
    public SlackHook alerting() {
        return new SlackHook();
    }
}
