package io.wegetit.sau.mongo;

import com.mongodb.lang.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class LocalDateTimeConverter {

    private LocalDateTimeConverter() {}

    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
        @Override
        public Date convert(@NonNull LocalDateTime source) {
            return new Date(source.atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
        }
    }

    public static class LocalDateTimeFromDateConverter implements Converter<Date, LocalDateTime> {
        @Override
        public LocalDateTime convert(@NonNull Date source) {
            return source.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        }
    }
}
