package io.wegetit.sau.system;

import io.wegetit.sau.utils.BaseConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSystemInfo.SystemInfoConfiguration.class)
public @interface EnableSystemInfo {

    @Configuration
    class SystemInfoConfiguration extends BaseConfiguration {

        @Validated
        @Bean
        @ConditionalOnMissingBean
        @ConfigurationProperties(prefix = "system.build")
        public SystemBuild systemBuild() {
            return new SystemBuild();
        }

        @Bean
        public SystemInfo systemInfo(SystemBuild systemBuild, Environment environment) {
            return SystemInfo.of(systemBuild, environment);
        }

        @Bean
        public SystemInfoRestService systemInfoRestService(SystemInfo systemInfo) {
            return new SystemInfoRestService(systemInfo);
        }
    }
}
