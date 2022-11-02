package io.wegetit.sau.data;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public interface IdEnum {

    String getId();

    static <T extends IdEnum> IdEnum fromId(Class<T> clazz, String value) {
        T[] values = clazz.getEnumConstants();
        return Stream.of(values).filter(p -> StringUtils.equals(p.getId(), value)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("IdEnum " + clazz.getSimpleName() + " with value " + value + " not defined"));
    }
}
