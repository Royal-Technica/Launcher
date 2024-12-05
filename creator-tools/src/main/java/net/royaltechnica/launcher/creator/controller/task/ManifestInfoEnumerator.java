/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.creator.controller.task;

import net.royaltechnica.launcher.creator.model.creator.ManifestEntry;
import net.royaltechnica.launcher.model.modpack.Manifest;
import net.royaltechnica.launcher.model.modpack.ManifestInfo;
import net.royaltechnica.launcher.persistence.Persistence;

import java.io.File;
import java.util.List;
import java.util.function.Function;

public class ManifestInfoEnumerator implements Function<List<ManifestEntry>, List<ManifestEntry>> {

    private final File searchDir;

    public ManifestInfoEnumerator(File searchDir) {
        this.searchDir = searchDir;
    }

    @Override
    public List<ManifestEntry> apply(List<ManifestEntry> entries) {
        File[] files = searchDir.listFiles(f -> f.isFile() && f.getName().toLowerCase().endsWith(".json") && !f.getName().startsWith("packages."));

        if (files != null) {
            for (File file : files) {
                String location = file.getName();
                Manifest manifest = Persistence.read(file, Manifest.class, true);

                if (manifest != null) {
                    ManifestInfo info = new ManifestInfo();
                    info.setName(manifest.getName());
                    info.setTitle(manifest.getTitle());
                    info.setVersion(manifest.getVersion());
                    info.setPriority(0);
                    info.setLocation(location);

                    boolean found = false;

                    for (ManifestEntry entry : entries) {
                        if (entry.getManifestInfo().getLocation().equals(location)) {
                            info.setPriority(entry.getManifestInfo().getPriority());
                            entry.setManifestInfo(info);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        ManifestEntry entry = new ManifestEntry();
                        entry.setManifestInfo(info);
                        entries.add(entry);
                    }
                }
            }
        }

        return entries;
    }

}
