package net.royaltechnica.launcher.model.minecraft;

import net.fasterxml.jackson.annotation.*;
import net.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.royaltechnica.launcher.model.minecraft.mapper.PlatformDeserializer;
import net.royaltechnica.launcher.model.minecraft.mapper.PlatformSerializer;
import net.royaltechnica.launcher.util.Environment;
import net.royaltechnica.launcher.util.Platform;
import lombok.Data;

import java.util.Map;
import java.util.regex.Pattern;

@Data
public class Rule {
	private Action action;
	private OS os;
	private Map<String, Boolean> features;

	private boolean doesOsMatch(Environment environment) {
		if (getOs() == null) {
			return true;
		} else {
			return getOs().matches(environment);
		}
	}

	private boolean doFeaturesMatch(FeatureList match) {
		if (getFeatures() == null) return true;

		return match.doesMatch(features);
	}

	public boolean matches(Environment environment, FeatureList match) {
		return doesOsMatch(environment) && doFeaturesMatch(match);
	}

	@JsonIgnore
	public boolean isAllowed() {
		return action == Action.ALLOW;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class OS {
		private Platform platform;
		private Pattern version;

		@JsonProperty("name")
		@JsonDeserialize(using = PlatformDeserializer.class)
		@JsonSerialize(using = PlatformSerializer.class)
		public Platform getPlatform() {
			return platform;
		}

		public boolean matches(Environment environment) {
			return (getPlatform() == null || getPlatform().equals(environment.getPlatform())) &&
					(getVersion() == null || getVersion().matcher(environment.getPlatformVersion()).matches());
		}
	}

	public enum Action {
		ALLOW,
		DISALLOW;

		@JsonCreator
		public static Action fromJson(String text) {
			return valueOf(text.toUpperCase());
		}

		@JsonValue
		public String toJson() {
			return name().toLowerCase();
		}
	}
}
