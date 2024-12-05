package net.royaltechnica.launcher.builder.loaders;

import net.fasterxml.jackson.databind.ObjectMapper;
import net.royaltechnica.launcher.model.modpack.Manifest;

import java.io.File;
import java.io.IOException;

public interface ILoaderProcessor {
	LoaderResult process(File loaderJar, Manifest manifest, ObjectMapper mapper, File baseDir) throws IOException;
}
