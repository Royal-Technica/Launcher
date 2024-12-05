/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.model.modpack;

import net.fasterxml.jackson.annotation.JsonBackReference;
import net.fasterxml.jackson.annotation.JsonSubTypes;
import net.fasterxml.jackson.annotation.JsonTypeInfo;
import net.royaltechnica.launcher.install.InstallExtras;
import net.royaltechnica.launcher.install.InstallLog;
import net.royaltechnica.launcher.install.Installer;
import net.royaltechnica.launcher.install.UpdateCache;
import net.royaltechnica.launcher.model.loader.ProcessorEntry;
import lombok.Data;
import lombok.ToString;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = FileInstall.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FileInstall.class, name = "file"),
        @JsonSubTypes.Type(value = ProcessorEntry.class, name = "process")
})
@Data
@ToString(exclude = "manifest")
public abstract class ManifestEntry {

    @JsonBackReference("manifest")
    private Manifest manifest;
    private Condition when;

    public abstract void install(Installer installer, InstallLog log, UpdateCache cache, InstallExtras extras) throws Exception;

}
