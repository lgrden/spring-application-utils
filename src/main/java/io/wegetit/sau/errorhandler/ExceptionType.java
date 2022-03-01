package io.wegetit.sau.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.function.Function;

@Getter
@AllArgsConstructor
@ToString(of = "errorClass")
@Builder
public class ExceptionType {
    private Class<? extends Throwable> errorClass;
    private HttpStatus status;
    private Function<Throwable, String> evalMessage;
    private boolean logTrace;

    public String getCode() {
        String code = errorClass.getSimpleName();
        code = code.replaceAll("()([A-Z])", "$1_$2");
        code = StringUtils.substringAfter(code, "_");
        code = StringUtils.upperCase(code);
        code = StringUtils.substringBefore(code, "_EXCEPTION");
        return code;
    }

    public String evaluateMessage(Throwable t) {
        return evalMessage != null ? evalMessage.apply(t) : t.getMessage();
    }

    public ExceptionTypeInfo toInfo() {
        return ExceptionTypeInfo.builder().status(getStatus().value()).statusText(getStatus()).code(getCode()).build();
    }
}
