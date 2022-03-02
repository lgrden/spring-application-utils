package io.wegetit.sau.slack;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SlackMessagingConfiguration.class)
public @interface EnableSlackMessaging {

}
