package org.iungo.cli.api;

public abstract class AbstractOpenConfigArgument implements Argument {

	protected final Argument name;

	public AbstractOpenConfigArgument(final Argument name) {
		super();
		this.name = name;
	}

	public Argument getName() {
		return name;
	}
}
