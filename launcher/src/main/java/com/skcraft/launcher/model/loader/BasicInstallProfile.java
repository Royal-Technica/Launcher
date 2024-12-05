package net.royaltechnica.launcher.model.loader;

import net.fasterxml.jackson.annotation.JsonIgnore;
import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicInstallProfile {
	private String profile;
	private int spec;

	@JsonProperty("install")
	private Legacy legacyProfile;

	@JsonIgnore
	public boolean isLegacy() {
		return getLegacyProfile() != null;
	}

	public String resolveProfileName() {
		if (isLegacy()) {
			return getLegacyProfile().getProfileName();
		} else {
			return getProfile();
		}
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Legacy {
		private String profileName;
	}
}
