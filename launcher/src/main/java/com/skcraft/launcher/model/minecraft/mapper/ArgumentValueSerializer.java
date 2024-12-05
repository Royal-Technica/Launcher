package net.royaltechnica.launcher.model.minecraft.mapper;

import net.fasterxml.jackson.core.JsonGenerationException;
import net.fasterxml.jackson.core.JsonGenerator;
import net.fasterxml.jackson.databind.SerializerProvider;
import net.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

public class ArgumentValueSerializer extends StdSerializer<List<String>> {
	protected ArgumentValueSerializer() {
		super(TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
	}

	@Override
	public void serialize(List<String> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		if (value.size() == 1) {
			jgen.writeString(value.get(0));
		} else {
			provider.defaultSerializeValue(value, jgen);
		}
	}
}
