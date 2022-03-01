package io.wegetit.sau.slack;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan("io.wegetit.sau.slack")
public @interface EnableSlackMessaging {

}
