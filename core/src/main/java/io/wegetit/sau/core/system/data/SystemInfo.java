package io.wegetit.sau.core.system.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.env.Environment;

@Getter
@Builder
@AllArgsConstructor
public class SystemInfo {

    private String applicationName;
    private SystemConnection connection;
    private SystemBuild build;

    public static SystemInfo of(SystemBuild systemBuild, Environment environment) {
        return SystemInfo.builder()
                .applicationName(environment.getProperty("spring.application.name"))
                .connection(SystemConnection.of(environment))
                .build(systemBuild)
                .build();
    }
}
