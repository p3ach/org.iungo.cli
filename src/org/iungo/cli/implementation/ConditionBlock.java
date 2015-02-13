package org.iungo.cli.implementation;

import org.iungo.cli.api.Argument;
import org.iungo.cli.api.Block;

public abstract class ConditionBlock extends Block {

	protected final Argument condition;

	public ConditionBlock(final Argument condition) {
		super();
		this.condition = condition;
	}
}
