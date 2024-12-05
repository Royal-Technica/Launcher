package net.royaltechnica.launcher.model.loader.profiles;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import net.royaltechnica.launcher.model.loader.VersionInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacyInstallProfile {
	@JsonProperty("install")
	private InstallData installData;
	private VersionInfo versionInfo;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class InstallData {
		private String path;
		private String filePath;
	}
}
