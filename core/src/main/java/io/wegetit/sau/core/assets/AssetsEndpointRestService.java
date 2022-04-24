package io.wegetit.sau.core.assets;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/assets/config")
public class AssetsEndpointRestService {

    @Value("${spring.profiles.active:#null}")
    private String profile;

    private String content;

    @PostConstruct
    private void init() {
        String path = StringUtils.isEmpty(profile) ? "config.json" : "config-" + profile + ".json";
        try {
            InputStream is = new ClassPathResource(path).getInputStream();
            content = IOUtils.toString(is, StandardCharsets.UTF_8);
            log.info("Read configuration from {} with content:\n{}", path, content);
        } catch (Exception e) {
            log.info("Configuration {} not found", path);
        }
    }

    @GetMapping
    public String config() {
        return content;
    }
}
