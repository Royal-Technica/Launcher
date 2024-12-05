package net.royaltechnica.launcher.model.loader;

import net.royaltechnica.launcher.model.modpack.DownloadableFile;
import lombok.Data;

import java.util.HashMap;

@Data
public class LocalLoader {
	private final LoaderManifest manifest;
	private final HashMap<String, DownloadableFile.LocalFile> localFiles;
}
