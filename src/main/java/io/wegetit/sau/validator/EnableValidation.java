package io.wegetit.sau.validator;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ValidationConfiguration.class)
public @interface EnableValidation {

}
