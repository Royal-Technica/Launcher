package net.royaltechnica.launcher.model.minecraft;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaVersion {
	private String component;
	private int majorVersion;
}
