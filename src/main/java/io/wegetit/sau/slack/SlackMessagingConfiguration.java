package io.wegetit.sau.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SlackMessagingConfiguration {

    @Bean
    public SlackMessageService slackMessageService(ObjectMapper objectMapper, RestTemplate restTemplate, SlackHook slackHook) {
        return new SlackMessageService(restTemplate, objectMapper, slackHook);
    }
}
