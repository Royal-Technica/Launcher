package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
	private String tokenType;
	private String accessToken;
	private String refreshToken;
}
