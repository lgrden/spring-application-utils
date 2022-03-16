package io.wegetit.sau.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SecurityAuthorizeUserRequest {
	@NotNull
	private String login;
	@NotNull
	private String password;
}
