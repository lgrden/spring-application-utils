package io.wegetit.sau.data;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class IdentifiedEnumUtils {

    private IdentifiedEnumUtils(){}

    public static <T extends IdentifiedEnum> String getId(T t) {
        return t != null ? t.getId() : null;
    }

    public static <T extends IdentifiedEnum> T findById(Class<T> clazz, String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        } else if (!clazz.isEnum()) {
            throw new IllegalStateException(clazz.getSimpleName() + " is not an enum");
        } else {
            T[] values = clazz.getEnumConstants();
            return Stream.of(values).filter(p -> StringUtils.equals(p.getId(), id)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Id " + id + " not found in " + clazz.getSimpleName()));
        }
    }
}
