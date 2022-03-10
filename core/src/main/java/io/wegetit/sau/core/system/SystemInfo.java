package io.wegetit.sau.core.system;

import io.wegetit.sau.core.json.JsonLocalDateTime;
import lombok.*;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SystemInfo {

    private String applicationName;
    private String serverAddress;
    private String serverPort;
    private String startDate;
    private String version;
    private String buildDate;
    private String gitCommit;
    private String gitTimeStamp;
    private String gitBranch;

    public static SystemInfo of(SystemBuild systemBuild, Environment environment) {
        return SystemInfo.builder()
                .applicationName(environment.getProperty("spring.application.name"))
                .serverAddress(environment.getProperty("server.address"))
                .serverPort(environment.getProperty("server.port"))
                .startDate(JsonLocalDateTime.DATE_TIME_FORMATTER.format(LocalDateTime.now()))
                .version(systemBuild.getVersion())
                .buildDate(systemBuild.getBuildDate())
                .gitCommit(systemBuild.getGitCommit())
                .gitTimeStamp(systemBuild.getGitTimeStamp())
                .gitBranch(systemBuild.getGitBranch())
                .build();
    }
}
