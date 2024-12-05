package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonParser;
import net.fasterxml.jackson.core.JsonProcessingException;
import net.fasterxml.jackson.core.JsonToken;
import net.fasterxml.jackson.databind.DeserializationContext;
import net.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.fasterxml.jackson.databind.exc.InvalidFormatException;
import net.royaltechnica.launcher.model.minecraft.GameArgument;

import java.io.IOException;

public class MinecraftArgumentsDeserializer extends StdDeserializer<GameArgument> {
	protected MinecraftArgumentsDeserializer() {
		super(GameArgument.class);
	}

	@Override
	public GameArgument deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (!jp.hasCurrentToken()) jp.nextToken();

		if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
			return jp.readValueAs(GameArgument.class);
		} else if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
			String argument = jp.getValueAsString();
			return new GameArgument(argument);
		}

		throw new InvalidFormatException(jp, "Invalid JSON type for deserializer (not string or object)", null, GameArgument.class);
	}
}
