package io.wegetit.sau.errorhandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

public class ExceptionMessageUtils {
    private static final String N_A = "N/A";
    private static final String SEPARATOR = ", ";

    private ExceptionMessageUtils() {}

    public static String getMessage(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(p -> StringUtils.isEmpty(p.getPropertyPath().toString()) ? p.getMessage() : p.getPropertyPath() + ": " + p.getMessage())
                .collect(Collectors.joining(SEPARATOR));
    }

    public static String getMessage(MethodArgumentNotValidException e) {
        return String.join(SEPARATOR,
                "Global errors: [" +
                        StringUtils.defaultIfEmpty(e.getBindingResult().getGlobalErrors().stream()
                                .map(p -> p.getObjectName() + ": " + p.getDefaultMessage())
                                .collect(Collectors.joining(SEPARATOR)), N_A) + "]",
                "Field errors: [" +
                        StringUtils.defaultIfEmpty(e.getBindingResult().getFieldErrors().stream()
                                .map(p -> p.getObjectName() + "." + p.getField() + ": " + p.getDefaultMessage())
                                .collect(Collectors.joining(SEPARATOR)), N_A) + "]"
        );
    }
}
