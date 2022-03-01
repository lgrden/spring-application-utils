package io.wegetit.sau.errorhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorHandlerService {
    private static final ExceptionType DEFAULT = ExceptionType.builder().errorClass(Exception.class)
            .status(HttpStatus.INTERNAL_SERVER_ERROR).logTrace(true).build();

    private final Map<Class<? extends Throwable>, ExceptionType> types = new HashMap<>();

    private final ObjectMapper objectMapper;

    public ErrorHandlerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        // global framework exceptions
        registerType(ExceptionType.builder().errorClass(ClientAbortException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(SizeLimitExceededException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(FileSizeLimitExceededException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(MethodArgumentNotValidException.class).status(HttpStatus.BAD_REQUEST)
                .evalMessage(e -> ExceptionMessageUtils.getMessage((MethodArgumentNotValidException)e)).build());
        registerType(ExceptionType.builder().errorClass(HttpRequestMethodNotSupportedException.class).status(HttpStatus.METHOD_NOT_ALLOWED).build());
        registerType(ExceptionType.builder().errorClass(MethodArgumentTypeMismatchException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(ConstraintViolationException.class).status(HttpStatus.BAD_REQUEST)
                .evalMessage(e -> ExceptionMessageUtils.getMessage((ConstraintViolationException)e)).build());
        registerType(ExceptionType.builder().errorClass(HttpMediaTypeNotSupportedException.class).status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build());
        registerType(ExceptionType.builder().errorClass(HttpMessageNotReadableException.class)
                .status(HttpStatus.BAD_REQUEST).evalMessage(e -> "Required request body is missing.").build());
        registerType(ExceptionType.builder().errorClass(MissingServletRequestParameterException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(MissingServletRequestPartException.class).status(HttpStatus.BAD_REQUEST).build());
        registerType(ExceptionType.builder().errorClass(SecurityException.class).status(HttpStatus.FORBIDDEN).build());
    }

    public ExceptionTypeInfo getDefaultTypeInfo() {
        return DEFAULT.toInfo();
    }
    public List<ExceptionTypeInfo> getTypesInfo() {
        return types.values().stream().map(ExceptionType::toInfo)
            .sorted(Comparator.comparing(ExceptionTypeInfo::getCode))
            .collect(Collectors.toList());
    }

    public void registerType(ExceptionType type) {
        types.put(type.getErrorClass(), type);
    }

    public Optional<ExceptionType> findExceptionType(Throwable t) {
        if (t != null) {
            for (Class<? extends Throwable> type : types.keySet()) {
                if (type.isAssignableFrom(t.getClass())) {
                    return Optional.of(types.get(type));
                }
            }
        }
        return Optional.empty();
    }

    public Throwable findException(Throwable t) {
        while (t != null) {
            for (Class<? extends Throwable> type: types.keySet()) {
                if (type.isAssignableFrom(t.getClass())) {
                    return t;
                }
            }
            t = t.getCause();
        }
        return null;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handle(Throwable throwable, HttpServletRequest request) {
        Throwable handlerException = findException(throwable);
        ExceptionType type = findExceptionType(handlerException).orElse(DEFAULT);
        String message = handlerException != null ? type.evaluateMessage(handlerException) : type.evaluateMessage(throwable);
        String logMessage = "[" + type.getStatus().value() + "]" + type.getStatus().getReasonPhrase() + ": " + message;
        if (type.isLogTrace()) {
            log.error(logMessage, throwable);
        } else {
            log.error(logMessage);
        }
        return new ResponseEntity<>(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(type.getStatus().value())
                .statusText(type.getStatus())
                .code(type.getCode())
                .message(message)
                .path(request.getContextPath() + request.getServletPath())
                .build(), type.getStatus());
    }
}
