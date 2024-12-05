/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.install;

import net.royaltechnica.concurrency.ProgressObservable;
import net.royaltechnica.launcher.Launcher;

public interface InstallTask extends ProgressObservable {

    void execute(Launcher launcher) throws Exception;

}
