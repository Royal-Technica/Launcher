package net.royaltechnica.launcher.model.loader;

import net.royaltechnica.launcher.install.*;
import net.royaltechnica.launcher.model.minecraft.Side;
import net.royaltechnica.launcher.model.modpack.ManifestEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProcessorEntry extends ManifestEntry {
	private String loaderName;
	private InstallProcessor processor;

	@Override
	public void install(Installer installer, InstallLog log, UpdateCache cache, InstallExtras extras) throws Exception {
		LocalLoader loader = extras.getLoader(loaderName);

		if (processor.shouldRunOn(Side.CLIENT)) {
			installer.queueLate(new ProcessorTask(processor, loader.getManifest(), getManifest(), loader.getLocalFiles()));
		}
	}
}
