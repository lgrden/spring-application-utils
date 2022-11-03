package io.wegetit.sau.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@EqualsAndHashCode(of = "clazz")
public class DefaultRowMapper<T> implements RowMapper<T> {

    private boolean quiet = false;

    @Getter
    private Class<T> clazz;

    private Map<String, Field> fields = new HashMap<>();

    public static <T> DefaultRowMapper<T> of(Class<T> clazz) {
        return new DefaultRowMapper<T>(clazz);
    }

    public DefaultRowMapper<T> quiet(boolean quiet) {
        this.quiet = quiet;
        return this;
    }

    public DefaultRowMapper(Class<T> clazz) {
        this.clazz = clazz;
        Class<?> start = clazz;
        while (start != null) {
            Stream.of(start.getDeclaredFields()).forEach(f -> fields.put(fieldName(f.getName()), f));
            start = start.getSuperclass();
        }
    }

    private String fieldName(String name) {
        return name.toLowerCase().replaceAll("_", "");
    }

    @SneakyThrows
    @Override
    public T mapRow(ResultSet rs, int rowNum) {
        T target = clazz.getDeclaredConstructor().newInstance();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            String name = fieldName(rs.getMetaData().getColumnName(i));
            Field field = fields.get(name);
            if (field == null) {
                if (!quiet) {
                    log.warn("Field " + name + " not found in " + clazz.getSimpleName());
                }
                continue;
            }
            field.setAccessible(true);
            if (field != null) {
                field.set(target, getValue(field.getType(),rs, i));
            }
        }
        return target;
    }

    private Object getValue(Class<?> type, ResultSet rs, int idx) throws SQLException {
        if (rs.getObject(idx) == null) {
            return null;
        }
        if (type.isEnum() && IdentifiedEnum.class.isAssignableFrom(type)) {
            return IdentifiedEnumUtils.findById((Class<? extends IdentifiedEnum>) type, rs.getString(idx));
        }
        if (type.isEnum()) {
            return Enum.valueOf((Class<Enum>) type, rs.getString(idx));
        }
        return rs.getObject(idx, type);
    }
}
