package io.wegetit.sau.core.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wegetit.sau.shared.BaseConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSlackMessaging.SlackMessagingConfiguration.class)
public @interface EnableSlackMessaging {

    @Configuration
    class SlackMessagingConfiguration extends BaseConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public SlackMessageService slackMessageService(RestTemplate restTemplate, ObjectMapper objectMapper, SlackHook slackHook) {
            return new SlackMessageService(restTemplate, objectMapper, slackHook);
        }
    }
}
