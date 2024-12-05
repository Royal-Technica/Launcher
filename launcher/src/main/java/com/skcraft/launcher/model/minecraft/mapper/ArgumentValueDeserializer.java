package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonParser;
import net.fasterxml.jackson.core.JsonProcessingException;
import net.fasterxml.jackson.core.JsonToken;
import net.fasterxml.jackson.databind.DeserializationContext;
import net.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.fasterxml.jackson.databind.exc.InvalidFormatException;
import net.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArgumentValueDeserializer extends StdDeserializer<List<String>> {
	protected ArgumentValueDeserializer() {
		super(TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
	}

	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (!jp.hasCurrentToken()) jp.nextToken();

		if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
			String[] allValues = jp.readValueAs(String[].class);
			return Arrays.asList(allValues);
		} else if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
			String value = jp.readValueAs(String.class);
			return Lists.newArrayList(value);
		}

		throw new InvalidFormatException(jp, "Invalid JSON type for deserializer (not string or array)", null, List.class);
	}
}
