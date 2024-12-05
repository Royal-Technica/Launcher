package net.royaltechnica.launcher.install;

import net.royaltechnica.launcher.model.loader.LocalLoader;
import lombok.Data;

import java.io.File;
import java.util.HashMap;

@Data
public class InstallExtras {
	private final File contentDir;
	private final HashMap<String, LocalLoader> loaders;

	public LocalLoader getLoader(String key) {
		return loaders.get(key);
	}
}
