package io.wegetit.sau.core.errorhandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionTypeResponse {
    @JsonProperty("default")
    private ExceptionTypeInfo def;
    private List<ExceptionTypeInfo> list;
}
