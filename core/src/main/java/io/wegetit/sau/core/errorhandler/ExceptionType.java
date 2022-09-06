package io.wegetit.sau.core.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionType {
    private Class<? extends Throwable> errorClass;
    private HttpStatus status;
    private Function<Throwable, String> evalMessage;
    private Function<Throwable, ResponseEntity<ErrorResponse>> handler;
    private boolean logTrace;

    public String getCode() {
        String code = errorClass.getSimpleName();
        code = code.replaceAll("()([A-Z])", "$1_$2");
        code = StringUtils.substringAfter(code, "_");
        code = StringUtils.upperCase(code);
        code = StringUtils.substringBefore(code, "_EXCEPTION");
        code = StringUtils.substringBefore(code, "_ERROR");
        return code;
    }

    public String evaluateMessage(Throwable t) {
        return evalMessage != null ? evalMessage.apply(t) : t.getMessage();
    }

    public ExceptionTypeInfo toInfo() {
        HttpStatus status = ObjectUtils.defaultIfNull(getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ExceptionTypeInfo.builder().status(status.value()).statusText(status).code(getCode()).build();
    }
}
