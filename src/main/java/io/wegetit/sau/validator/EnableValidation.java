package io.wegetit.sau.validator;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan("io.wegetit.sau.validator")
public @interface EnableValidation {

}
