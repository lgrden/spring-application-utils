package io.wegetit.sau.core.system.data;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SystemBuild {
    @NotNull
    private String version;
    @NotNull
    private String buildDate;
    @NotNull
    private String gitCommit;
    @NotNull
    private String gitTimeStamp;
    @NotNull
    private String gitBranch;
}
