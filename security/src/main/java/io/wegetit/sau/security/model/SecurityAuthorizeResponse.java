package io.wegetit.sau.security.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.wegetit.sau.shared.json.JsonLocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SecurityAuthorizeResponse {
	private String login;
	private String token;
	@JsonSerialize(using = JsonLocalDateTime.Serializer.class)
	@JsonDeserialize(using = JsonLocalDateTime.Deserializer.class)
	private LocalDateTime expires;
}
