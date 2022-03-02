package io.wegetit.sau.system;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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
