package io.wegetit.sau.errorhandler;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan("io.wegetit.sau.errorhandler")
public @interface EnableErrorHandler {

}
