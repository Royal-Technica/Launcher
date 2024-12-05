package net.royaltechnica.launcher.model.minecraft;

import net.fasterxml.jackson.annotation.JsonIgnore;
import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.fasterxml.jackson.annotation.JsonProperty;
import net.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import net.royaltechnica.launcher.model.minecraft.mapper.ArgumentValueDeserializer;
import net.royaltechnica.launcher.model.minecraft.mapper.ArgumentValueSerializer;
import net.royaltechnica.launcher.util.Environment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class GameArgument {
	@JsonProperty("value")
	@JsonDeserialize(using = ArgumentValueDeserializer.class)
	@JsonSerialize(using = ArgumentValueSerializer.class)
	private List<String> values;
	private List<Rule> rules;

	public GameArgument(List<String> values) {
		this.values = values;
	}

	public GameArgument(String value) {
		this.values = Lists.newArrayList(value);
	}

	@JsonIgnore
	public String getJoinedValue() {
		return Joiner.on(' ').join(values);
	}

	public boolean shouldApply(Environment environment, FeatureList featureList) {
		if (getRules() == null) return true;

		boolean result = false;

		for (Rule rule : rules) {
			if (rule.matches(environment, featureList)) {
				result = rule.isAllowed();
			}
		}

		return result;
	}
}
