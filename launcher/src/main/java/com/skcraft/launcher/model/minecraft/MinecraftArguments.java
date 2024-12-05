package net.royaltechnica.launcher.model.minecraft;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import net.fasterxml.jackson.annotation.JsonTypeInfo;
import net.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.royaltechnica.launcher.model.minecraft.mapper.MinecraftArgumentsDeserializer;
import net.royaltechnica.launcher.model.minecraft.mapper.MinecraftArgumentsSerializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MinecraftArguments {
	@JsonProperty("game")
	@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
	@JsonSerialize(contentUsing = MinecraftArgumentsSerializer.class)
	@JsonDeserialize(contentUsing = MinecraftArgumentsDeserializer.class)
	private List<GameArgument> gameArguments = new ArrayList<>();

	@JsonProperty("jvm")
	@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
	@JsonSerialize(contentUsing =  MinecraftArgumentsSerializer.class)
	@JsonDeserialize(contentUsing = MinecraftArgumentsDeserializer.class)
	private List<GameArgument> jvmArguments = new ArrayList<>();
}

