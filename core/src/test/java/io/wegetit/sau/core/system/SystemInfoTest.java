package io.wegetit.sau.core.system;

import io.wegetit.sau.core.system.data.SystemInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SystemInfoApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SystemInfoTest {

    @Autowired
    private SystemInfoRestService systemInfoRestService;

    @Test
    public void getSystemInfo() {
        SystemInfo info = systemInfoRestService.getSystemInfo();
        assertEquals("spring-application-utils-core", info.getApplicationName());
        assertNotNull(info.getConnection());
        assertNotNull(info.getBuild());
    }
}
