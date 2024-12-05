package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class XstsError {
	@JsonProperty("XErr")
	private long xErr;
	private String message;
	private String redirect;
}
