/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonParser;
import net.fasterxml.jackson.databind.DeserializationContext;
import net.fasterxml.jackson.databind.JsonDeserializer;
import net.royaltechnica.launcher.util.Platform;

import java.io.IOException;

public class PlatformDeserializer extends JsonDeserializer<Platform> {

    @Override
    public Platform deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String text = jsonParser.getText();
        if (text.equalsIgnoreCase("windows")) {
            return Platform.WINDOWS;
        } else if (text.equalsIgnoreCase("linux")) {
            return Platform.LINUX;
        } else if (text.equalsIgnoreCase("solaris")) {
            return Platform.SOLARIS;
        } else if (text.equalsIgnoreCase("osx")) {
            return Platform.MAC_OS_X;
        } else {
            throw new IOException("Unknown platform: " + text);
        }
    }

}
