package io.wegetit.sau.core.system;

import io.wegetit.sau.core.system.data.SystemInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/system-info")
@Slf4j
public class SystemInfoRestService {

    private final SystemInfo systemInfo;

    @GetMapping
    public SystemInfo getSystemInfo() {
        return systemInfo;
    }
}
