/*
 * SKCraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher;

import net.royaltechnica.concurrency.ObservableFuture;
import net.royaltechnica.launcher.dialog.ProgressDialog;
import net.royaltechnica.launcher.swing.SwingHelper;
import net.royaltechnica.launcher.update.HardResetter;
import net.royaltechnica.launcher.update.Remover;
import net.royaltechnica.launcher.util.SharedLocale;

import java.awt.*;

import static net.royaltechnica.launcher.util.SharedLocale.tr;

public class InstanceTasks {

    private final Launcher launcher;

    public InstanceTasks(Launcher launcher) {
        this.launcher = launcher;
    }

    public ObservableFuture<Instance> delete(Window window, Instance instance) {
        // Execute the deleter
        Remover resetter = new Remover(instance);
        ObservableFuture<Instance> future = new ObservableFuture<Instance>(
                launcher.getExecutor().submit(resetter), resetter);

        // Show progress
        ProgressDialog.showProgress(
                window, future, SharedLocale.tr("instance.deletingTitle"), tr("instance.deletingStatus", instance.getTitle()));
        SwingHelper.addErrorDialogCallback(window, future);

        return future;
    }

    public ObservableFuture<Instance> hardUpdate(Window window, Instance instance) {
        // Execute the resetter
        HardResetter resetter = new HardResetter(instance);
        ObservableFuture<Instance> future = new ObservableFuture<Instance>(
                launcher.getExecutor().submit(resetter), resetter);

        // Show progress
        ProgressDialog.showProgress(window, future, SharedLocale.tr("instance.resettingTitle"),
                tr("instance.resettingStatus", instance.getTitle()));
        SwingHelper.addErrorDialogCallback(window, future);

        return future;
    }

    public ObservableFuture<InstanceList> reloadInstances(Window window) {
        InstanceList.Enumerator loader = launcher.getInstances().createEnumerator();
        ObservableFuture<InstanceList> future = new ObservableFuture<InstanceList>(launcher.getExecutor().submit(loader), loader);

        ProgressDialog.showProgress(window, future, SharedLocale.tr("launcher.checkingTitle"), SharedLocale.tr("launcher.checkingStatus"));
        SwingHelper.addErrorDialogCallback(window, future);

        return future;
    }

}
