package io.wegetit.sau.log.http;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HttpRequestLoggerConfiguration.class)
public @interface EnableHttpRequestLogger {

}
