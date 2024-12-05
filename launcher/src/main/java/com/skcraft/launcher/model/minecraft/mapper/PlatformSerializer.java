/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonGenerator;
import net.fasterxml.jackson.core.JsonProcessingException;
import net.fasterxml.jackson.databind.JsonSerializer;
import net.fasterxml.jackson.databind.SerializerProvider;
import net.royaltechnica.launcher.util.Platform;

import java.io.IOException;

public class PlatformSerializer extends JsonSerializer<Platform> {

    @Override
    public void serialize(Platform platform, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        switch (platform) {
            case WINDOWS:
                jsonGenerator.writeString("windows");
                break;
            case MAC_OS_X:
                jsonGenerator.writeString("osx");
                break;
            case LINUX:
                jsonGenerator.writeString("linux");
                break;
            case SOLARIS:
                jsonGenerator.writeString("solaris");
                break;
            case UNKNOWN:
                jsonGenerator.writeNull();
                break;
        }
    }

}
