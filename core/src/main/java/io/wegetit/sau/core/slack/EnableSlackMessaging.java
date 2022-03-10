package io.wegetit.sau.core.slack;

import io.wegetit.sau.shared.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSlackMessaging.SlackMessagingConfiguration.class)
public @interface EnableSlackMessaging {

    @Configuration
    class SlackMessagingConfiguration extends BaseConfiguration {

        @Bean
        public SlackMessageService slackMessageService(SlackHook slackHook) {
            return new SlackMessageService(slackHook);
        }
    }
}
