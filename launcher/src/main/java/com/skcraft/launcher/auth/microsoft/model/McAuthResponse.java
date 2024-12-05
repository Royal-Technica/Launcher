package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.annotation.JsonIgnore;
import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class McAuthResponse {
	private String accessToken;
	private String tokenType;
	private int expiresIn;

	@JsonIgnore
	public String getAuthorization() {
		return String.format("%s %s", tokenType, accessToken);
	}
}
