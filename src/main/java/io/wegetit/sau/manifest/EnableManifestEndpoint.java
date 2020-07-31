package io.wegetit.sau.manifest;

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
@Import({ManifestEndpointConfiguration.class})
@PropertySource({"classpath:manifest.properties"})
public @interface EnableManifestEndpoint {

}
