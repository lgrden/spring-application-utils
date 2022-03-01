package io.wegetit.sau.errorhandler;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ErrorHandlerConfiguration.class)
public @interface EnableErrorHandler {

}
