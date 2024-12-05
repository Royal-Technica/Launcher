package net.royaltechnica.launcher.model.modpack;

import net.royaltechnica.launcher.install.Installer;
import net.royaltechnica.launcher.model.minecraft.Side;
import lombok.Data;
import lombok.NonNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static net.royaltechnica.launcher.LauncherUtils.concat;

@Data
public class DownloadableFile {
	private String name;
	private String hash;
	private String location;
	private Side side;
	private int size;

	public LocalFile download(@NonNull Installer installer, Manifest manifest) throws MalformedURLException {
		URL url = concat(manifest.getObjectsUrl(), getLocation());

		File local = installer.getDownloader().download(url, hash, size, name);
		return new LocalFile(local, name);
	}

	@Data
	public static class LocalFile {
		private final File location;
		private final String name;
	}
}
