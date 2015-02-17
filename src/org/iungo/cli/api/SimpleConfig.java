package org.iungo.cli.api;


public class SimpleConfig extends AbstractConfig {

	public SimpleConfig(final String name) {
		super(name);
	}

	@Override
	public String toString() {
		return String.format("%s [\n%s\n]", SimpleConfig.class.getName(), super.toString());
	}
}
