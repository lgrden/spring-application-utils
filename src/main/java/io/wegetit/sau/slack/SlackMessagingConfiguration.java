package io.wegetit.sau.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class SlackMessagingConfiguration extends BaseConfiguration {

    @Bean
    public SlackMessageService slackMessageService(RestTemplate restTemplate, ObjectMapper objectMapper, SlackHook slackHook) {
        return new SlackMessageService(restTemplate, objectMapper, slackHook);
    }
}
