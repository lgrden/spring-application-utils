package io.wegetit.sau.core.message;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;

@AllArgsConstructor
public class MessageResolver {
    public final MessageSource messageSource;
    public final MessageResolverProperties properties;

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, properties.getDefaultLocale());
    }

    public String getMessage(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}
