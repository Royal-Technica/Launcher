package net.royaltechnica.launcher.model.loader;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExtendedSidedData<T> extends SidedData<T> {
	private T common;
}
