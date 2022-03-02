package io.wegetit.sau.mongo;

import com.mongodb.lang.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class LocalDateConverter {

    private LocalDateConverter() {}

    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {
        @Override
        public Date convert(@NonNull LocalDate source) {
            return new Date(source.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
        }
    }

    public static class LocalDateFromDateConverter implements Converter<Date, LocalDate> {
        @Override
        public LocalDate convert(@NonNull Date source) {
            return source.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        }
    }
}
