/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.creator.controller.task;

import com.google.common.base.Function;
import net.royaltechnica.concurrency.ProgressObservable;
import net.royaltechnica.launcher.creator.model.creator.Pack;
import net.royaltechnica.launcher.creator.model.creator.Workspace;

import java.util.List;

public class PackLoader implements ProgressObservable, Function<Workspace, List<Pack>> {

    private int index;
    private int size = 0;
    private Pack lastPack;

    @Override
    public List<Pack> apply(Workspace workspace) {
        List<Pack> packs = workspace.getPacks();
        size = packs.size();

        for (Pack pack : packs) {
            lastPack = pack;
            pack.load();
            index++;
        }

        lastPack = null;

        return packs;
    }

    @Override
    public double getProgress() {
        if (size == 0) {
            return -1;
        } else {
            return index / (double) size;
        }
    }

    @Override
    public String getStatus() {
        Pack pack = lastPack;
        if (pack != null) {
            return "Loading " + pack.getDirectory().getName() + "...";
        } else {
            return "Enumerating packs...";
        }
    }

}
