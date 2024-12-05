/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.creator.controller.task;

import net.royaltechnica.concurrency.ProgressObservable;
import net.royaltechnica.launcher.LauncherException;
import net.royaltechnica.launcher.LauncherUtils;
import net.royaltechnica.launcher.builder.PackageBuilder;
import net.royaltechnica.launcher.creator.model.creator.Pack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PackBuilder implements Callable<PackBuilder>, ProgressObservable {

    private final Pack pack;
    private final File outputDir;
    private final String version;
    private final String manifestFilename;
    private final boolean clean;
    private final boolean downloadUrls;

    public PackBuilder(Pack pack, File outputDir, String version, String manifestFilename, boolean clean, boolean downloadUrls) {
        this.pack = pack;
        this.outputDir = outputDir;
        this.version = version;
        this.manifestFilename = manifestFilename;
        this.clean = clean;
        this.downloadUrls = downloadUrls;
    }

    @Override
    public PackBuilder call() throws Exception {
        if (clean) {
            List<File> failures = new ArrayList<File>();

            try {
                LauncherUtils.interruptibleDelete(outputDir, failures);
            } catch (IOException e) {
                Thread.sleep(1000);
                LauncherUtils.interruptibleDelete(outputDir, failures);
            }

            if (failures.size() > 0) {
                throw new LauncherException(failures.size() + " failed to delete", "There were " + failures.size() + " failures during cleaning.");
            }
        }

        //noinspection ResultOfMethodCallIgnored
        outputDir.mkdirs();

        System.setProperty("net.royaltechnica.builder.ignoreURLOverrides", downloadUrls ? "false" : "true");
        String[] args = {
                "--version", version,
                "--manifest-dest", new File(outputDir, manifestFilename).getAbsolutePath(),
                "-i", pack.getDirectory().getAbsolutePath(),
                "-o", outputDir.getAbsolutePath()
        };
        PackageBuilder.main(args);

        return this;
    }

    @Override
    public double getProgress() {
        return -1;
    }

    @Override
    public String getStatus() {
        return "Building modpack...";
    }
}
