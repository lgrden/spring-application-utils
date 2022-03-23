package io.wegetit.sau.core.message;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;
import java.nio.charset.StandardCharsets;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableMessageResolver.EnableMessageResolverConfiguration.class)
public @interface EnableMessageResolver {

    @Slf4j
    @Configuration
    class EnableMessageResolverConfiguration extends BaseConfiguration {

        @Bean
        @Validated
        @ConfigurationProperties(prefix = "messages")
        public MessageResolverProperties messageResolverProperties() {
            return new MessageResolverProperties();
        }

        @Bean
        public MessageSource messageSource(MessageResolverProperties properties) {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setBasenames(properties.getBaseNames().toArray(String[]::new));
            source.setDefaultLocale(properties.getDefaultLocale());
            source.setDefaultEncoding(StandardCharsets.UTF_8.name());
            source.setUseCodeAsDefaultMessage(true);
            log.info("Using messages with default locale {} and base names {}", properties.getDefaultLocale(), properties.getBaseNames());
            return source;
        }

        @Bean
        public MessageResolver messageResolver(MessageSource messageSource, MessageResolverProperties properties) {
            return new MessageResolver(messageSource, properties);
        }
    }
}
