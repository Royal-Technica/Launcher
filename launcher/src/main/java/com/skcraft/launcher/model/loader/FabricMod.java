package net.royaltechnica.launcher.model.loader;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabricMod implements Versionable {
	private String id;
	private String name;
	private String version;
}
