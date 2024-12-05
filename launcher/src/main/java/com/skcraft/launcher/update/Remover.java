/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.update;

import net.royaltechnica.concurrency.ProgressObservable;
import net.royaltechnica.launcher.Instance;
import net.royaltechnica.launcher.LauncherException;
import net.royaltechnica.launcher.LauncherUtils;
import net.royaltechnica.launcher.persistence.Persistence;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static net.royaltechnica.launcher.LauncherUtils.checkInterrupted;
import static net.royaltechnica.launcher.util.SharedLocale.tr;

public class Remover implements Callable<Instance>, ProgressObservable {

    private final Instance instance;

    public Remover(@NonNull Instance instance) {
        this.instance = instance;
    }

    @Override
    public double getProgress() {
        return -1;
    }

    @Override
    public String getStatus() {
        return tr("instanceDeleter.deleting", instance.getDir());
    }

    @Override
    public Instance call() throws Exception {
        instance.setInstalled(false);
        instance.setUpdatePending(true);
        Persistence.commitAndForget(instance);

        checkInterrupted();

        Thread.sleep(2000);

        List<File> failures = new ArrayList<File>();

        try {
            LauncherUtils.interruptibleDelete(instance.getDir(), failures);
        } catch (IOException e) {
            Thread.sleep(1000);
            LauncherUtils.interruptibleDelete(instance.getDir(), failures);
        }

        if (failures.size() > 0) {
            throw new LauncherException(failures.size() + " failed to delete",
                     tr("instanceDeleter.failures", failures.size()));
        }

        return instance;
    }

}
