package io.wegetit.sau.core.system;

import io.wegetit.sau.shared.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSystemInfo.SystemInfoConfiguration.class)
public @interface EnableSystemInfo {

    @Configuration
    class SystemInfoConfiguration extends BaseConfiguration {

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
