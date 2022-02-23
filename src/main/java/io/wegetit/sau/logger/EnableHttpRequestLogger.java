package io.wegetit.sau.logger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableHttpRequestLoggerConfiguration.class)
public @interface EnableHttpRequestLogger {

}
