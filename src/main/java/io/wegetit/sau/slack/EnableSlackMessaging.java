package io.wegetit.sau.slack;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SlackMessagingConfiguration.class})
@PropertySource({"classpath:slack.properties"})
public @interface EnableSlackMessaging {

}
