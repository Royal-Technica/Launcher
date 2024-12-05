package net.royaltechnica.launcher;

import net.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.royaltechnica.launcher.launch.MemorySettings;
import net.royaltechnica.launcher.launch.runtime.JavaRuntime;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceSettings {
	private JavaRuntime runtime;
	private MemorySettings memorySettings;
	private String customJvmArgs;
}
