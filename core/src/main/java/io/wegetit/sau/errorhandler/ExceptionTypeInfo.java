package io.wegetit.sau.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionTypeInfo {
    private int status;
    private HttpStatus statusText;
    private String code;
}
