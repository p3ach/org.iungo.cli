package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;

public abstract class ConditionBlock extends DefaultBlock {

	protected final Argument condition;

	public ConditionBlock(final Argument condition) {
		super();
		this.condition = condition;
	}
}
