package io.wegetit.sau.system;

import io.wegetit.sau.json.JsonLocalDateTime;
import lombok.*;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SystemInfo {

    private String applicationName;
    private String serverIp;
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
                .serverIp(environment.getProperty("host.ip"))
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
