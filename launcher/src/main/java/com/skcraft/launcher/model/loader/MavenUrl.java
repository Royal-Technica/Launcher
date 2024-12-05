package net.royaltechnica.launcher.model.loader;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import net.royaltechnica.launcher.model.minecraft.Library;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MavenUrl {
	@JsonProperty("maven")
	private String name;
	private String url;
	private String version;
	private boolean stable;

	public Library toLibrary() {
		Library library = new Library();
		library.setName(name);
		library.setUrl(url);

		return library;
	}
}
