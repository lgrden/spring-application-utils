package io.wegetit.sau.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class BaseConfiguration {

    @PostConstruct
    private void init() {
        log.info("Configuration {} has been loaded.", StringUtils.substringBefore(getClass().getSimpleName(), "$"));
    }
}
