package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class XblAuthProperties {
	private String authMethod = "RPS";
	private String siteName = "user.auth.xboxlive.com";
	@NonNull
	private String rpsTicket;
}
