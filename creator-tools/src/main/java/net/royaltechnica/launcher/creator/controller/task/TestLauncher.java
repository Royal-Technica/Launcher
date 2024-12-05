/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.creator.controller.task;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.royaltechnica.concurrency.ProgressObservable;
import net.royaltechnica.launcher.Instance;
import net.royaltechnica.launcher.InstanceList;
import net.royaltechnica.launcher.Launcher;
import net.royaltechnica.launcher.auth.Session;
import net.royaltechnica.launcher.launch.LaunchOptions;
import net.royaltechnica.launcher.launch.LaunchOptions.UpdatePolicy;
import net.royaltechnica.launcher.swing.SwingHelper;

import java.awt.*;
import java.util.List;

public class TestLauncher implements Function<InstanceList, Instance>, ProgressObservable {

    private final Launcher launcher;
    private final Window window;
    private final String id;
    private final Session session;

    public TestLauncher(Launcher launcher, Window window, String id, Session session) {
        this.launcher = launcher;
        this.window = window;
        this.id = id;
        this.session = session;
    }

    private Optional<Instance> findInstance(List<Instance> instances) {
        for (Instance instance : instances) {
            if (instance.getName().equals(id)) {
                return Optional.fromNullable(instance);
            }
        }

        return Optional.absent();
    }

    @Override
    public Instance apply(InstanceList instanceList) {
        Optional<Instance> optional = findInstance(instanceList.getInstances());

        if (optional.isPresent()) {
            LaunchOptions options = new LaunchOptions.Builder()
                    .setInstance(optional.get())
                    .setUpdatePolicy(UpdatePolicy.ALWAYS_UPDATE)
                    .setWindow(window)
                    .setSession(session)
                    .build();

            launcher.getLaunchSupervisor().launch(options);

            return optional.get();
        } else {
            SwingHelper.showErrorDialog(window,
                    "After generating the necessary files, it appears the modpack can't be found in the " +
                            "launcher. Did you change modpack.json while the launcher was launching?", "Launch Error");

            return null;
        }
    }

    @Override
    public double getProgress() {
        return -1;
    }

    @Override
    public String getStatus() {
        return "Launching the game...";
    }

}
