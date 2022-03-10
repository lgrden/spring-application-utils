package io.wegetit.sau.core.errorhandler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.wegetit.sau.shared.json.JsonLocalDateTime;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String path;
    @JsonSerialize(using = JsonLocalDateTime.Serializer.class)
    @JsonDeserialize(using = JsonLocalDateTime.Deserializer.class)
    private LocalDateTime timestamp;
    private int status;
    private HttpStatus statusText;
    private String code;
    private String message;
}
