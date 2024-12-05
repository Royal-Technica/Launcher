package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.annotation.JsonIgnore;
import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class XboxAuthResponse {
	private String token;
	private DisplayClaims displayClaims;

	@JsonIgnore
	public String getUhs() {
		return getDisplayClaims().getXui().get(0).getUhs();
	}

	@Data
	public static class DisplayClaims {
		private List<UhsContainer> xui;
	}

	@Data
	public static class UhsContainer {
		private String uhs;
	}
}
