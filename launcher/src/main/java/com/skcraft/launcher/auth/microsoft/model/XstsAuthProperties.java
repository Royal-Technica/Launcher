package net.royaltechnica.launcher.auth.microsoft.model;

import net.fasterxml.jackson.databind.PropertyNamingStrategies;
import net.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class XstsAuthProperties {
	private String sandboxId = "RETAIL";
	private List<String> userTokens;

	public XstsAuthProperties(String token) {
		this.userTokens = Collections.singletonList(token);
	}
}
