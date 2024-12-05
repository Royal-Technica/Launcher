package net.royaltechnica.launcher.builder.loaders;

import com.google.common.collect.Lists;
import net.royaltechnica.launcher.model.minecraft.Library;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class LoaderResult {
	private final List<Library> loaderLibraries = Lists.newArrayList();
	private final List<Library> processorLibraries = Lists.newArrayList();
	private final List<URL> jarMavens = Lists.newArrayList();
}
