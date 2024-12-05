package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonGenerationException;
import net.fasterxml.jackson.core.JsonGenerator;
import net.fasterxml.jackson.databind.SerializerProvider;
import net.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.royaltechnica.launcher.model.minecraft.GameArgument;

import java.io.IOException;

public class MinecraftArgumentsSerializer extends StdSerializer<GameArgument> {
	protected MinecraftArgumentsSerializer() {
		super(GameArgument.class);
	}

	@Override
	public void serialize(GameArgument value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		if (value.getValues().size() == 1 && (value.getRules() == null || value.getRules().size() == 0)) {
			jgen.writeString(value.getValues().get(0));
		} else {
			provider.defaultSerializeValue(value, jgen);
		}
	}
}
