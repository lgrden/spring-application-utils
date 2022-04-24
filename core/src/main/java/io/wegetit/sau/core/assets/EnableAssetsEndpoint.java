package io.wegetit.sau.core.assets;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableAssetsEndpoint.EAssetsEndpointConfiguration.class)
public @interface EnableAssetsEndpoint {

    @Configuration
    class EAssetsEndpointConfiguration extends BaseConfiguration {

        @Bean
        public AssetsEndpointRestService assetsEndpointRestService() {
            return new AssetsEndpointRestService();
        }
    }
}
