package io.wegetit.sau.shared;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class BaseConfiguration {

    @PostConstruct
    private void init() {
        log.info("Configuration {} has been enabled.", StringUtils.substringBefore(getClass().getSimpleName(), "$"));
    }

    protected <T> T getOrDefault(ObjectProvider<T> op, T t) {
        op.ifAvailable(x -> {
            log.info("Using defined bean {}.", x.getClass().getSimpleName());
        });
        return op.getIfAvailable(() -> {
            log.info("Default {} initialized.", t.getClass().getSimpleName());
            return t;
        });
    }
}
