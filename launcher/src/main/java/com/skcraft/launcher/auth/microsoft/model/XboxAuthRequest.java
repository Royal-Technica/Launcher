package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class XboxAuthRequest<T> {
	@NonNull private T properties;
	private String relyingParty = "http://auth.xboxlive.com";
	private String tokenType = "JWT";
}
